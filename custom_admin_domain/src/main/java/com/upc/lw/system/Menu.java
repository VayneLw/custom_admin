package com.upc.lw.system;

import com.upc.lw.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description
 * @author: liwei
 * @date: 2020/8/11 15:37
 */
@Entity
@Table(name = "sys_menu")
@Data
public class Menu extends BaseEntity implements Serializable {

    @Id
    @Column(name="menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="pid")
    private Long parentId;

    private String name;

    private String component;

    private String path;

    private String title;

    private String icon;

}
