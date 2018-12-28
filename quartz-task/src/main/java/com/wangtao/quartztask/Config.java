//package com.wangtao.quartztask;
//
//import javax.persistence.*;
//
///**
// * @author : wangtao
// * @date : 2018/12/20 9:54  星期四
// */
//
//
//@Entity
//public class Config {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column
//    private String cron;
//
//    /**
//     * @return the id
//     */
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getCron() {
//        return cron;
//    }
//
//    public void setCron(String cron) {
//        this.cron = cron;
//    }
//
//    @Override
//    public String toString() {
//        return "id= " + id + ",cron=" + cron;
//    }
//}
