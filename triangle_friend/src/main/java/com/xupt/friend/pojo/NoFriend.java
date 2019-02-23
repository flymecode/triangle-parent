package com.xupt.friend.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@IdClass(NoFriend.class)
@Table(name = "tb_nofriend")
public class NoFriend implements Serializable {

    @Id
    private String userid;
    @Id
    private String friendid;

}
