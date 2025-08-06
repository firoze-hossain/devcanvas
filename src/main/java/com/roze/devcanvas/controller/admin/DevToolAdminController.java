package com.roze.devcanvas.controller.admin;

import com.roze.devcanvas.entity.DevTool;
import com.roze.devcanvas.service.interfaces.DevToolService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/admin/dev-tools")
public class DevToolAdminController {
    private final DevToolService devToolService;

    @Autowired
    public DevToolAdminController(DevToolService devToolService) {
        this.devToolService = devToolService;
    }

    @GetMapping({"", "/", "/list"})
    public String listDevTools(Model theModel) {
        List<DevTool> devTools = devToolService.getAllDevTools();
        theModel.addAttribute("devTools", devTools);
        return "admin/dev-tools/list-dev-tools";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        DevTool devTool = new DevTool();
        theModel.addAttribute("devTool", devTool);
        return "admin/dev-tools/dev-tool-form";
    }

    @PostMapping("/save")
    public String saveDevTool(@ModelAttribute("devTool") DevTool devTool,
                              @RequestParam(value = "windowsFile", required = false) MultipartFile windowsFile,
                              @RequestParam(value = "macFile", required = false) MultipartFile macFile,
                              @RequestParam(value = "linuxDebFile", required = false) MultipartFile linuxDebFile,
                              @RequestParam(value = "linuxRpmFile", required = false) MultipartFile linuxRpmFile,
                              @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {

        // Handle file uploads
        if (windowsFile != null && !windowsFile.isEmpty()) {
            String windowsPath = saveFile(windowsFile, "windows");
            devTool.setWindowsDownloadUrl("/admin/dev-tools/download/" + windowsPath);
        }

        if (macFile != null && !macFile.isEmpty()) {
            String macPath = saveFile(macFile, "mac");
            devTool.setMacDownloadUrl("/admin/dev-tools/download/" + macPath);
        }

        if (linuxDebFile != null && !linuxDebFile.isEmpty()) {
            String linuxDebPath = saveFile(linuxDebFile, "linux-deb");
            devTool.setLinuxDebDownloadUrl("/admin/dev-tools/download/" + linuxDebPath);
        }

        if (linuxRpmFile != null && !linuxRpmFile.isEmpty()) {
            String linuxRpmPath = saveFile(linuxRpmFile, "linux-rpm");
            devTool.setLinuxRpmDownloadUrl("/admin/dev-tools/download/" + linuxRpmPath);
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            String imagePath = saveFile(imageFile, "image");
            devTool.setImagePath(imagePath);
        }

        devToolService.save(devTool);
        return "redirect:/admin/dev-tools";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("devToolId") int id, Model theModel) {
        DevTool devTool = devToolService.findById(id);
        theModel.addAttribute("devTool", devTool);
        return "admin/dev-tools/dev-tool-form";
    }
    @GetMapping("/delete")
    public String deleteDevTool(@RequestParam("devToolId") int id) {
        devToolService.deleteById(id);
        return "redirect:/admin/dev-tools";
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws IOException {
        Path path = Paths.get("uploads/dev-tools/" + filename);
        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("Could not read file: " + filename);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    private String saveFile(MultipartFile file, String prefix) throws IOException {
        String fileName = prefix + "-" + System.currentTimeMillis() + "." +
                FilenameUtils.getExtension(file.getOriginalFilename());
        Path path = Paths.get("uploads/dev-tools/" + fileName);
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());
        return fileName;
    }
}