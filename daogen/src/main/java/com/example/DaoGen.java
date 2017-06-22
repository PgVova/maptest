package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoGen {
    private static final int VERSION = 1;
    private Schema schema;

    public static void main(String[] args) {
        new DaoGen().start();
    }

    private void start(){
        try {
            schema = new Schema(VERSION, "com.mmh.cash.domain.entities");
            schema.enableKeepSectionsByDefault();


//            generateBanners();

            new DaoGenerator().generateAll(schema, "app/src/main/java");
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    private void generateBanners(){
        Entity entity = schema.addEntity("Banners");
        entity.addStringProperty("url").notNull();
        entity.addStringProperty("size").notNull();
    }

}
