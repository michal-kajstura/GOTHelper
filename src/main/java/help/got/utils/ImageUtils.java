package help.got.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageUtils {

	/**
	 * @param imageFile File containing image
	 * @return Image encoded as base64 string
	 * @throws IOException when image file does not exist or it cannot be encoded as base64 string
	 */
    	public static String getImageAsBase64(File imageFile) throws IOException {

		var image = ImageIO.read(imageFile);
		var baos = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", baos);
		baos.flush();
		byte[] imageBytes = baos.toByteArray();
		baos.close();
		return Base64.encodeBase64String(imageBytes);

	}
}
