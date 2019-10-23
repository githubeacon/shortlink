package com.beacon.shortlink.entity;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.io.Serializable;

/**
 * @author beacon
 */

public class Link implements Serializable {
    private static final long serialVersionUID = 3293495159116662377L;

    private Long id;
    private String longLink;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongLink() {
        return longLink;
    }

    public void setLongLink(String longLink) {
        this.longLink = longLink;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", longLink='" + longLink + '\'' +
                '}';
    }
}
