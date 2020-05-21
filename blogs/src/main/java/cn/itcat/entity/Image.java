package cn.itcat.entity;

import java.io.Serializable;

public class Image implements Serializable {
    private Integer id;
    private String path;
    private String filename;

    public Image() {
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
