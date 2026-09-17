package com.piratesgaming.client;

import java.io.*;
import java.nio.file.*;
import java.util.Properties;
import net.minecraft.client.MinecraftClient;

public final class ClientSettings {
    public boolean piratesMode=false, motionBlur=false, lowFire=false, smallShield=false, smallTotem=false;
    public boolean particles=false, animations=false, zoom=false, fullbright=false, bossBar=true, scoreboard=true;
    public boolean armorHud=true, fpsHud=true, cpsHud=true, pingHud=true, coordsHud=true, tpsHud=true, keystrokes=true;
    public boolean entityCulling=true, fastRender=true, chunkOptimization=true, reduceLagSpikes=true, memoryOptimization=true;
    public boolean shaderSupport=true, crystalOptimizer=true, swordOptimizer=true, pearlOptimizer=true, rocketOptimizer=true;
    public boolean customCape=false, customTotem=false;
    public int crosshairSize=8, crosshairGap=3, crosshairThickness=2, crosshairOpacity=100;
    public int hudScale=100;
    public String capePath="", totemPath="";
    private Path file(){ return MinecraftClient.getInstance().runDirectory.toPath().resolve("piratesclient.properties"); }
    public void load(){
        Properties p=new Properties(); Path f=file(); if(!Files.exists(f)) return;
        try(InputStream in=Files.newInputStream(f)){p.load(in);}catch(IOException ignored){}
        piratesMode=b(p,"piratesMode",piratesMode); motionBlur=b(p,"motionBlur",motionBlur); lowFire=b(p,"lowFire",lowFire); smallShield=b(p,"smallShield",smallShield); smallTotem=b(p,"smallTotem",smallTotem);
        particles=b(p,"particles",particles); animations=b(p,"animations",animations); zoom=b(p,"zoom",zoom); fullbright=b(p,"fullbright",fullbright); bossBar=b(p,"bossBar",bossBar); scoreboard=b(p,"scoreboard",scoreboard);
        armorHud=b(p,"armorHud",armorHud); fpsHud=b(p,"fpsHud",fpsHud); cpsHud=b(p,"cpsHud",cpsHud); pingHud=b(p,"pingHud",pingHud); coordsHud=b(p,"coordsHud",coordsHud); tpsHud=b(p,"tpsHud",tpsHud); keystrokes=b(p,"keystrokes",keystrokes);
        entityCulling=b(p,"entityCulling",entityCulling); fastRender=b(p,"fastRender",fastRender); chunkOptimization=b(p,"chunkOptimization",chunkOptimization); reduceLagSpikes=b(p,"reduceLagSpikes",reduceLagSpikes); memoryOptimization=b(p,"memoryOptimization",memoryOptimization); shaderSupport=b(p,"shaderSupport",shaderSupport);
        crystalOptimizer=b(p,"crystalOptimizer",crystalOptimizer); swordOptimizer=b(p,"swordOptimizer",swordOptimizer); pearlOptimizer=b(p,"pearlOptimizer",pearlOptimizer); rocketOptimizer=b(p,"rocketOptimizer",rocketOptimizer);
        customCape=b(p,"customCape",customCape); customTotem=b(p,"customTotem",customTotem); capePath=p.getProperty("capePath",""); totemPath=p.getProperty("totemPath","");
        crosshairSize=i(p,"crosshairSize",crosshairSize); crosshairGap=i(p,"crosshairGap",crosshairGap); crosshairThickness=i(p,"crosshairThickness",crosshairThickness); crosshairOpacity=i(p,"crosshairOpacity",crosshairOpacity); hudScale=i(p,"hudScale",hudScale);
    }
    public void save(){
        Properties p=new Properties();
        set(p,"piratesMode",piratesMode); set(p,"motionBlur",motionBlur); set(p,"lowFire",lowFire); set(p,"smallShield",smallShield); set(p,"smallTotem",smallTotem); set(p,"particles",particles); set(p,"animations",animations); set(p,"zoom",zoom); set(p,"fullbright",fullbright); set(p,"bossBar",bossBar); set(p,"scoreboard",scoreboard);
        set(p,"armorHud",armorHud); set(p,"fpsHud",fpsHud); set(p,"cpsHud",cpsHud); set(p,"pingHud",pingHud); set(p,"coordsHud",coordsHud); set(p,"tpsHud",tpsHud); set(p,"keystrokes",keystrokes); set(p,"entityCulling",entityCulling); set(p,"fastRender",fastRender); set(p,"chunkOptimization",chunkOptimization); set(p,"reduceLagSpikes",reduceLagSpikes); set(p,"memoryOptimization",memoryOptimization); set(p,"shaderSupport",shaderSupport);
        set(p,"crystalOptimizer",crystalOptimizer); set(p,"swordOptimizer",swordOptimizer); set(p,"pearlOptimizer",pearlOptimizer); set(p,"rocketOptimizer",rocketOptimizer); set(p,"customCape",customCape); set(p,"customTotem",customTotem); p.setProperty("capePath",capePath); p.setProperty("totemPath",totemPath);
        set(p,"crosshairSize",crosshairSize); set(p,"crosshairGap",crosshairGap); set(p,"crosshairThickness",crosshairThickness); set(p,"crosshairOpacity",crosshairOpacity); set(p,"hudScale",hudScale);
        try{Files.createDirectories(file().getParent()); try(OutputStream out=Files.newOutputStream(file())){p.store(out,"Pirates Client settings");}}catch(IOException ignored){}
    }
    public void applyPiratesMode(){
        boolean v=piratesMode; entityCulling=v; fastRender=v; chunkOptimization=v; reduceLagSpikes=v; memoryOptimization=v; crystalOptimizer=v; swordOptimizer=v; pearlOptimizer=v; rocketOptimizer=v; particles=!v; animations=!v; lowFire=v; smallShield=v; smallTotem=v; shaderSupport=true; save();
    }
    private static boolean b(Properties p,String k,boolean d){return Boolean.parseBoolean(p.getProperty(k,Boolean.toString(d)));} private static int i(Properties p,String k,int d){try{return Integer.parseInt(p.getProperty(k,Integer.toString(d)));}catch(Exception e){return d;}} private static void set(Properties p,String k,Object v){p.setProperty(k,String.valueOf(v));}
}
