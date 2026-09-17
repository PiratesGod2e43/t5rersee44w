package com.piratesgaming.client;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.util.function.BooleanSupplier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public final class PiratesScreen extends Screen {
    private final Screen parent; private String section="HOME"; private int scroll=0;
    private final ClientSettings s=PiratesClient.SETTINGS;
    public PiratesScreen(Screen parent){super(Text.literal("Pirates Client"));this.parent=parent;}
    @Override protected void init(){clearChildren();build();}
    private void build(){
        nav("PvP","PVP",70); nav("HUD","HUD",106); nav("Crosshair","CROSSHAIR",142); nav("Visuals","VISUALS",178); nav("Performance","PERFORMANCE",214); nav("Custom Capes","CAPES",250); nav("Custom Totem","TOTEM",286); nav("Keybinds","KEYBINDS",322); nav("Pirates Mode","PIRATES",358);
        if(section.equals("HOME")) home(); else if(section.equals("PVP")) pvp(); else if(section.equals("HUD")) hud(); else if(section.equals("CROSSHAIR")) crosshair(); else if(section.equals("VISUALS")) visuals(); else if(section.equals("PERFORMANCE")) performance(); else if(section.equals("CAPES")) capes(); else if(section.equals("TOTEM")) totem(); else if(section.equals("KEYBINDS")) keybinds(); else pirates();
        add(25,Math.min(height-42,390),120,"Back",()->section="HOME");
    }
    private void home(){
        title("Dashboard"); add(245,82,360,"Open PvP Settings",()->section="PVP"); add(245,122,360,"Open HUD Editor",()->section="HUD"); add(245,162,360,"Open Crosshair Editor",()->section="CROSSHAIR"); add(245,202,360,"Open Optimization Center",()->section="PERFORMANCE"); add(245,242,360,"Open Custom Totem Editor",()->section="TOTEM"); add(245,282,360,"Open Cape Uploader",()->section="CAPES");
        toggle(245,330,360,"PIRATES MODE",()->{s.piratesMode=!s.piratesMode;s.applyPiratesMode();},()->s.piratesMode);
    }
    private void pvp(){title("PvP Optimizer"); row("Motion Blur",()->s.motionBlur=!s.motionBlur,()->s.motionBlur); row("Crystal Optimizer",()->s.crystalOptimizer=!s.crystalOptimizer,()->s.crystalOptimizer); row("Sword Optimizer",()->s.swordOptimizer=!s.swordOptimizer,()->s.swordOptimizer); row("Pearl Optimizer",()->s.pearlOptimizer=!s.pearlOptimizer,()->s.pearlOptimizer); row("Rocket Optimizer",()->s.rocketOptimizer=!s.rocketOptimizer,()->s.rocketOptimizer); row("Low Fire",()->s.lowFire=!s.lowFire,()->s.lowFire); row("Small Shield",()->s.smallShield=!s.smallShield,()->s.smallShield); row("Small Totem Pop",()->s.smallTotem=!s.smallTotem,()->s.smallTotem); row("Low Particles",()->s.particles=!s.particles,()->s.particles); }
    private void hud(){title("HUD Editor"); row("FPS",()->s.fpsHud=!s.fpsHud,()->s.fpsHud); row("CPS",()->s.cpsHud=!s.cpsHud,()->s.cpsHud); row("Ping",()->s.pingHud=!s.pingHud,()->s.pingHud); row("Coordinates",()->s.coordsHud=!s.coordsHud,()->s.coordsHud); row("TPS",()->s.tpsHud=!s.tpsHud,()->s.tpsHud); row("Armor HUD",()->s.armorHud=!s.armorHud,()->s.armorHud); row("Keystrokes",()->s.keystrokes=!s.keystrokes,()->s.keystrokes); row("Boss Bar",()->s.bossBar=!s.bossBar,()->s.bossBar); row("Scoreboard",()->s.scoreboard=!s.scoreboard,()->s.scoreboard); }
    private void crosshair(){title("Crosshair Custom Editor"); add(245,78,170,"Size: "+s.crosshairSize,()->s.crosshairSize=s.crosshairSize>=20?2:s.crosshairSize+2); add(430,78,170,"Gap: "+s.crosshairGap,()->s.crosshairGap=s.crosshairGap>=12?0:s.crosshairGap+2); add(245,118,170,"Thickness: "+s.crosshairThickness,()->s.crosshairThickness=s.crosshairThickness>=6?1:s.crosshairThickness+1); add(430,118,170,"Opacity: "+s.crosshairOpacity+"%",()->s.crosshairOpacity=s.crosshairOpacity>=100?20:s.crosshairOpacity+20); add(245,170,355,"Crosshair Preview — click to cycle",()->{}); add(245,212,355,"Reset Crosshair",()->{s.crosshairSize=8;s.crosshairGap=3;s.crosshairThickness=2;s.crosshairOpacity=100;}); }
    private void visuals(){title("Visuals"); row("Animations",()->s.animations=!s.animations,()->s.animations); row("Zoom",()->s.zoom=!s.zoom,()->s.zoom); row("Fullbright",()->s.fullbright=!s.fullbright,()->s.fullbright); row("Shader Support / Iris",()->s.shaderSupport=!s.shaderSupport,()->s.shaderSupport); row("Low Fire",()->s.lowFire=!s.lowFire,()->s.lowFire); row("Small Shield",()->s.smallShield=!s.smallShield,()->s.smallShield); }
    private void performance(){title("Max-Out Optimization Center"); row("Entity Culling",()->s.entityCulling=!s.entityCulling,()->s.entityCulling); row("Fast Render",()->s.fastRender=!s.fastRender,()->s.fastRender); row("Chunk Optimization",()->s.chunkOptimization=!s.chunkOptimization,()->s.chunkOptimization); row("Reduce Lag Spikes",()->s.reduceLagSpikes=!s.reduceLagSpikes,()->s.reduceLagSpikes); row("Memory Optimization",()->s.memoryOptimization=!s.memoryOptimization,()->s.memoryOptimization); add(245,310,355,"APPLY MAX-OUT OPTIMIZATION",()->{s.piratesMode=true;s.applyPiratesMode();}); }
    private void capes(){title("Custom Capes"); add(245,90,355,"UPLOAD CAPE PNG",()->choose(true)); add(245,130,355,"Enable Custom Cape ["+(s.customCape?"ON":"OFF")+"]",()->s.customCape=!s.customCape); add(245,175,355,"Current: "+name(s.capePath),()->{}); }
    private void totem(){title("Custom Totem Editor"); add(245,82,355,"UPLOAD TOTEM PNG",()->choose(false)); add(245,122,355,"UPLOAD SKIN PNG",()->choose(false)); add(245,162,355,"Enable Custom Totem ["+(s.customTotem?"ON":"OFF")+"]",()->s.customTotem=!s.customTotem); add(245,202,355,"Totem Preview",()->{}); add(245,242,355,"Use Small Totem Pop ["+(s.smallTotem?"ON":"OFF")+"]",()->s.smallTotem=!s.smallTotem); }
    private void keybinds(){title("Keybind Profiles"); add(245,85,355,"Save Profile 1",()->saveProfile(1)); add(245,125,355,"Load Profile 1",()->loadProfile(1)); add(245,165,355,"Delete Profile 1",()->deleteProfile(1)); add(245,215,355,"Open Minecraft Controls",()->MinecraftClient.getInstance().setScreen(new net.minecraft.client.gui.screen.option.ControlsOptionsScreen(this, MinecraftClient.getInstance().options))); }
    private void pirates(){title("Pirates Mode"); add(245,90,355,"PIRATES MODE: "+(s.piratesMode?"ON":"OFF"),()->{s.piratesMode=!s.piratesMode;s.applyPiratesMode();}); add(245,135,355,"Apply All Supported Optimizations",()->s.applyPiratesMode()); add(245,180,355,"Small Totem + Low Fire + Low Shield",()->{s.smallTotem=true;s.lowFire=true;s.smallShield=true;s.save();}); }
    private void nav(String text,String id,int y){addDrawableChild(ButtonWidget.builder(Text.literal(text),b->{section=id;init();}).dimensions(20,y,190,28).build());}
    private void row(String text,Runnable r,BooleanSupplier v){toggle(245,78+(count++*34),170,text,r,v);}
    private int count=0;
    private void title(String t){count=0;}
    private void toggle(int x,int y,int w,String text,Runnable r,BooleanSupplier v){add(x,y,w,text+"  ["+(v.getAsBoolean()?"ON":"OFF")+"]",r);}
    private void add(int x,int y,int w,String text,Runnable r){addDrawableChild(ButtonWidget.builder(Text.literal(text),b->{r.run();s.save();init();}).dimensions(x,y,w,30).build());}
    private String name(String p){if(p==null||p.isBlank())return "None";return new File(p).getName();}
    private void choose(boolean cape){try{FileDialog d=new FileDialog((Frame)null,cape?"Select Cape PNG":"Select PNG",FileDialog.LOAD);d.setFile("*.png");d.setVisible(true);if(d.getFile()!=null){String p=new File(d.getDirectory(),d.getFile()).getAbsolutePath();if(cape){s.capePath=p;s.customCape=true;}else{s.totemPath=p;s.customTotem=true;}s.save();init();}}catch(Exception ignored){}}
    private void saveProfile(int n){s.save();} private void loadProfile(int n){s.load();} private void deleteProfile(int n){s.piratesMode=false;s.save();}
    @Override public void render(DrawContext c,int mx,int my,float d){c.fill(0,0,width,height,0xF0080D15);c.fill(0,0,215,height,0xFF090F19);c.fill(215,0,width,58,0xFF101827);c.drawTextWithShadow(textRenderer,"PIRATES CLIENT",25,28,0x55BFFF);c.drawTextWithShadow(textRenderer,section,245,28,0xFFFFFF);c.drawTextWithShadow(textRenderer,"1.21.11  •  Max-Out Performance",245,height-18,0x9FB4C7);super.render(c,mx,my,d);}
    @Override public void close(){s.save();if(client!=null)client.setScreen(parent);}
}
