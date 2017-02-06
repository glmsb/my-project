package com.example.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import org.kobjects.base64.Base64;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class ImageTools {

	public static Bitmap DrawableToBitMap(Drawable drawable) {
		Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
				drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		drawable = null;
		return bitmap;
	}

	public static void StoreToSD(Bitmap bitmap, String PicName) {
		File fileDir = new File(Environment.getExternalStorageDirectory().toString() +  "/AAAAA/100ANDRO");
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		File imageFile = new File(fileDir, PicName);// 目录，图片名称
		try {
			imageFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(imageFile);
			bitmap.compress(CompressFormat.PNG, 50, fos);// 格式为png,jpeg背景为黑色
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		bitmap.recycle();
		// bitmap = null;
	}

	/**
	 * 根据路径显示图片
	 * 
	 * @param fileName
	 * @param n
	 *            width，hight设为原来的n分一
	 * @return Drawable
	 */

	public static Bitmap readBitMap(String fileName, int n) {// 根据名字读取图片
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inSampleSize = n; // width，hight设为原来的n分一
		opt.inPurgeable = true;
		opt.inInputShareable = true; // 获取资源图片
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Bitmap bitmap = BitmapFactory.decodeStream(fis, null, opt);
		// Drawable drawable = new BitmapDrawable(bitmap);
		System.gc();
		return bitmap;
	}

	/**
	 * 根据图片路径压缩图片,并转为Base64string
	 * 把图片信息通过Base64转换成字符串 
	 * @param picPath
	 *            图片路径
	 * @return
	 * @throws IOException
	 */
	public static String imageToBase64String(String picPath) throws IOException {

		Bitmap bitmap;

		bitmap = readBitMap(picPath, 1);// 读出图片
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if (picPath.endsWith(".jpg") || picPath.endsWith(".jpeg")) {
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		} else if (picPath.endsWith(".png")) {
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		}

		int options = 90;
		while (baos.toByteArray().length / 1024 > 200)// 图片大于200KB
		{
			baos.reset();// 重置baos
			if (picPath.endsWith(".jpg") || picPath.endsWith(".jpeg")) {
				bitmap.compress(CompressFormat.JPEG, options, baos);// 这里压缩为options%
			}
			if (picPath.endsWith(".png")) {
				bitmap.compress(CompressFormat.PNG, options, baos);// 这里压缩为options%
			}

			options -= 20;// 每次减少20
		}
		bitmap.recycle();
		bitmap = null;
		// 进行Base64编码

		String base64String = new String(Base64.encode(baos.toByteArray()));
		baos.reset();
		baos.close();
		return base64String;
	}

	public Bitmap decodeFile(File f, boolean isSmall) {
		try {
			// decode image size
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			FileInputStream stream1 = new FileInputStream(f);
			BitmapFactory.decodeStream(stream1, null, o);
			stream1.close();

			// Find the correct scale value. It should be the power of 2.
			final int REQUIRED_SIZE = 70;
			int width_tmp = o.outWidth, height_tmp = o.outHeight;
			int scale = 1;
			if (isSmall) {
				while (true) {
					if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
						break;
					width_tmp /= 2;
					height_tmp /= 2;
					scale *= 2;
				}

			}

			// decode with inSampleSize
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			FileInputStream stream2 = new FileInputStream(f);
			Bitmap bitmap = BitmapFactory.decodeStream(stream2, null, o2);
			stream2.close();
			return bitmap;
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

//	 public Object imageToBase64StringList(Context context,
//	 List<Image_Info> picPath) throws IOException {
//	 FrameApplication application = (FrameApplication) context
//	 .getApplicationContext();
//	 Bitmap bitmap;
//	 for (int i = 0; i < picPath.size(); i++) {
//	 bitmap = readBitMap(picPath.get(i).file_Path, 1);// 读出图片
//	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
//	 if (picPath.get(i).getFile_ExtName().equals(".jpg")) {
//	 bitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
//	 } else if (picPath.get(i).getFile_ExtName().equals(".png")) {
//	 bitmap.compress(Bitmap.CompressFormat.PNG, 90, baos);
//	 }
//	
//	 int options = 90;
//	 while (baos.toByteArray().length / 1024 > 200)// 图片大于200KB
//	 {
//	 baos.reset();// 重置baos
//	 if (picPath.get(i).getFile_ExtName().equals(".jpg")) {
//	 bitmap.compress(CompressFormat.JPEG, options, baos);// 这里压缩为options%
//	 }
//	 if (picPath.get(i).getFile_ExtName().equals(".png")) {
//	 bitmap.compress(CompressFormat.PNG, options, baos);// 这里压缩为options%
//	 }
//	
//	 options -= 20;// 每次减少20
//	 }
//	 bitmap.recycle();
//	 bitmap = null;
//	 // 进行Base64编码
//	
//	 String base64String = new String(Base64.encode(baos.toByteArray()));
//	 baos.reset();
//	 baos.close();
//	 Image_Info info = new Image_Info();
//	 info.setFile_Path(picPath.get(i).getFile_Path());
//	 info.setFile_Name(picPath.get(i).getFile_Name());
//	 info.setFile_ExtName(picPath.get(i).getFile_ExtName());
//	 info.setFile_String(base64String);
//	 application.Imagelist.set(i, info);
//	 }
//	 return application.Imagelist;
//	 }
	
	
	/** 

     * 图片上传方法 

     *  

     * 1.把图片信息通过Base64转换成字符串 

     * 2.调用connectWebService方法实现上传 

     */  
	
/*	private Handler handler=new Handler();
    Runnable runnable=new Runnable() {
		public void run() {
			  Toast.makeText(getBaseContext(), "cccc", Toast.LENGTH_LONG).show();
		}
	};
	

    private void testUpload(){    

        try{      

            FileInputStream fis = new FileInputStream(fileName);    

            final ByteArrayOutputStream baos = new ByteArrayOutputStream();    

            byte[] buffer = new byte[1024];    

            int count = 0;    

            while((count = fis.read(buffer)) >= 0){    

                baos.write(buffer, 0, count);    

            }    
           
			
            final String uploadBuffer = new String(Base64.encode(baos.toByteArray()));  //进行Base64编码              
            new Thread() {
				public void run() {
		            connectWebService(uploadBuffer);    
					handler.post(runnable);					
				}
			}.start();

            Log.i("connectWebService", "start");    

            fis.close();    

        }catch(Exception e){    

            e.printStackTrace();    

        }    

    }   

      

    *//** 

     * 通过webservice实现图片上传 

     *  

     * @param imageBuffer 

     *//*  

    private void connectWebService(String imageBuffer) {    

        SoapObject soapObject = new SoapObject(namespace, methodName);        

        soapObject.addProperty("filename", "my.jpg");  //参数1   图片名    

        soapObject.addProperty("image", imageBuffer);   //参数2  图片字符串    

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);    

        envelope.dotNet = false;    

        envelope.setOutputSoapObject(soapObject);    

        HttpTransportSE httpTranstation = new HttpTransportSE(url);    

        try {    

            httpTranstation.call(namespace, envelope);    

            Object result = envelope.getResponse();    

            Log.i("connectWebService", result.toString());    


        } catch (Exception e) {    

            e.printStackTrace();  
        }    

    }    
*/
}
