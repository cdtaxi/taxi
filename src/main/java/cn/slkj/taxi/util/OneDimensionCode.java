package cn.slkj.taxi.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code128Encoder;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;

public class OneDimensionCode {
	public static void main(String[] args) {
		try {
			JBarcode localJBarcode = new JBarcode(Code128Encoder.getInstance(), WidthCodedPainter.getInstance(), BaseLineTextPainter.getInstance());
			String str = "130323199310050029";
			BufferedImage localBufferedImage = localJBarcode.createBarcode(str);
			saveToPNG(localBufferedImage, "Code39.png");
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	static void saveToJPEG(BufferedImage paramBufferedImage, String paramString) {
		saveToFile(paramBufferedImage, paramString, "jpeg");
	}

	static void saveToPNG(BufferedImage paramBufferedImage, String paramString) {
		saveToFile(paramBufferedImage, paramString, "png");
	}

	static void saveToGIF(BufferedImage paramBufferedImage, String paramString) {
		saveToFile(paramBufferedImage, paramString, "gif");
	}

	static void saveToFile(BufferedImage paramBufferedImage, String paramString1, String paramString2) {
		try {
			FileOutputStream localFileOutputStream = new FileOutputStream("/Users/mr.li/Desktop/code/" + paramString1);
			ImageUtil.encodeAndWrite(paramBufferedImage, paramString2, localFileOutputStream, 96, 96);
			localFileOutputStream.close();
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	public static InputStream saveToInputStream(BufferedImage paramBufferedImage) {
		InputStream in = null;
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(paramBufferedImage, "png", os);
			in = new ByteArrayInputStream(os.toByteArray());
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return in;
	}
}
