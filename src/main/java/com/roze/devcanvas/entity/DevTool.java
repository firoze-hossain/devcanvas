package com.roze.devcanvas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dev_tools")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DevTool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "windows_download_url")
    private String windowsDownloadUrl;
    
    @Column(name = "mac_download_url")
    private String macDownloadUrl;
    
    @Column(name = "linux_deb_download_url")
    private String linuxDebDownloadUrl;
    
    @Column(name = "linux_rpm_download_url")
    private String linuxRpmDownloadUrl;
    
    @Column(name = "image_path")
    private String imagePath;

}