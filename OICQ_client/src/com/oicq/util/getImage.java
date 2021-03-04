package com.oicq.util;

import java.awt.Image;
import javax.swing.ImageIcon;

public class getImage {
	public static ImageIcon getImage(ImageIcon icon,int width,int height){
        Image image=icon.getImage().getScaledInstance(width, height,Image.SCALE_REPLICATE);
        icon.setImage(image);
        return icon;
    }
}
