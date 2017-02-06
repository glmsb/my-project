package com.example.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.abstractAtactivity.BaseActivity;
import com.example.util.LogUtil;
import com.example.util.ImageTools;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class FirstActivity extends BaseActivity implements OnClickListener {	
	private Button btn1,btn2,btn3,btn5,btn6,btn7,btn9,btn10,btn11,btn13;	
	private ImageButton btn4,btn8,btn16;
	private ImageView btn12;
	private Uri imageUri;
	private String PicName = new SimpleDateFormat("yyyymmdd_hhmmss").format(new Date()) + ".jpg";
	public static final int USE_INTENT = 0;
	public static final int TAKE_PHOTO = 1;
	public static final int SELECT_PHOTO = 2;
	public static final int CROP_PHOTO = 3;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.first_layout);
		btn1=(Button) findViewById(R.id.button_1);
		btn2=(Button) findViewById(R.id.button_2);
		btn3=(Button) findViewById(R.id.button_3);
		btn4=(ImageButton) findViewById(R.id.button_4);
		btn5=(Button) findViewById(R.id.progress_dialog);
		btn6=(Button) findViewById(R.id.alert_dialog);
		btn7=(Button) findViewById(R.id.date_picker_dialog);
		btn8=(ImageButton) findViewById(R.id.camera);
		btn9=(Button) findViewById(R.id.receiver);
		btn10=(Button) findViewById(R.id.notification);
		btn11=(Button) findViewById(R.id.assets);
		btn12=(ImageView) findViewById(R.id.contacts);
		btn13=(Button) findViewById(R.id.library);
		btn16=(ImageButton) findViewById(R.id.message);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btn10.setOnClickListener(this);
		btn11.setOnClickListener(this);
		btn12.setOnClickListener(this);
		btn13.setOnClickListener(this);
		btn16.setOnClickListener(this);
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	//使用实现接口方式注册监听器来监听按钮的点击事件
	public void onClick(View v) {
		switch (v.getId()) { //获取按钮的id

		case R.id.button_1:
			
			/**
			 * 显式intent启动活动，传值出去
			 */
			String data1="活动2需要的参数1";
			String data2="活动2需要的参数2";
			//活动2中定义的方法
			SecondActivity.actionStart(FirstActivity.this, data1, data2);
	
			break;
					
			
		case R.id.button_2:
			
			/**
			通过隐式 Intent来启动活动,并有值传回来
			 */
			Intent intent=new Intent("com.example.activitytest.ACTION_START");
			intent.addCategory("com.example.activitytest.MY");
			startActivityForResult(intent, USE_INTENT);
			
			break;
			
			
			/**
			 使用隐式 Intent，我们不仅可以启动自己程序内的活动，还可以启动其他程序的活动，
			 这使得 Android多个应用程序之间的功能共享成为了可能
			 */
		case R.id.button_3:
			
			/**
			调用系统的浏览器来打开这个网页
			 */	
			String url="http://www.baidu.com";
			Intent intent1=new Intent(Intent.ACTION_VIEW);// Android 系统内置的动作
			intent1.putExtra("url", url);
			intent1.setData(Uri.parse(url));//用于指定当前 Intent正在操作的数据
			startActivity(intent1);

			break;
			
			
		case R.id.button_4:
			
			/**
			调用系统拨号界面
			Intent.ACTION_CALL,需要加上权限，直接调用系统拨号
			 */

			Intent intent2=new Intent(Intent.ACTION_DIAL);
			intent2.setData(Uri.parse("tel:18559650956"));
			startActivity(intent2);
			break;
			
			
		case R.id.progress_dialog:
			
			/**
			 * 在对话框中显示一个圆形进度条，可以屏蔽其他控件,
			 * 使用静态方式创建并显示，这种进度条只能是圆形条,设置title和Message提示内容 ,
			 * 这里最后一个参数boolean indeterminate设置是否是不明确的状态
			 */			
			final ProgressDialog dialog = ProgressDialog.show(this,  "提示", "正在登陆中.....", false);
			//定义处理消息的对象  
			  final   Handler handler = new Handler(new Handler.Callback() {  
		        //加入传消息来了就这么么办  
 			public boolean handleMessage(Message msg){  
		        	dialog.dismiss();//对话框消失  
		            Toast.makeText(getBaseContext(), "登陆成功", Toast.LENGTH_LONG).show();
					return false;     
		        }  
		    });  
		    
			new Thread(new Runnable() {			
				@Override
				public void run() {
					try {
						//处理耗时操作，比如连接网络
						Thread.sleep(10000);//模拟后台操作
						
						// cancel和dismiss方法本质都是一样的，都是从屏幕中删除Dialog,唯一的区别是  
	                    // 调用cancel方法会回调DialogInterface.OnCancelListener如果注册的话,dismiss方法不会回掉  
						//dialog.cancel();
						//dialog.dismiss();
					
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					handler.sendEmptyMessage(0);//传送消息  
				}
			}).start();
			
			
			
			/**
			 * 在对话框中显示一个水平进度条，可以屏蔽其他控件
			 */
			final ProgressDialog progressDialog=new ProgressDialog(this);//构建出一个 ProgressDialog 对象
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 设置水平进度条  
			progressDialog.setIcon(R.drawable.ic_launcher);//设置title的图标，默认是没有的，如果没有设置title的话只设置Icon是不会显示图标的  
			progressDialog.setTitle("应用下载中....");
			progressDialog.setMessage("请稍等片刻，我正在拼命加载！！");
			progressDialog.setCancelable(false);// 设置是否可以通过点击Back键取消  
			progressDialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条 
			progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"取消", new DialogInterface.OnClickListener() {				
				@Override
				public void onClick(DialogInterface dialog, int whichButton) {
					// TODO Auto-generated method stub					
				}
			});
			
			progressDialog.setButton(DialogInterface.BUTTON_POSITIVE,"暂停", new DialogInterface.OnClickListener() {				
				@Override
				public void onClick(DialogInterface dialog, int whichButton) {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(3000);
						progressDialog.getButton(whichButton).setText("继续");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
					
			progressDialog.show();
			
			
			new Thread(new Runnable() {			
				@Override
				public void run() {
					for (int i=1; i<=5; i++) {
					    try {
					    	//处理耗时操作，比如连接网络
					        Thread.sleep(1000);
					        
					       // 更新进度条的进度,可以在子线程中更新进度条进度
					        progressDialog.incrementProgressBy((int)(100/5));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}    
					}
					//当数据加载完成后必须要调用 ProgressDialog的dismiss()方法来关闭对话框
					 progressDialog.dismiss();
					   
					    
				}
			}).start();
	
			break;
					
			
			
		case R.id.alert_dialog:
			
			/**
			用于提示一些非常重要的内容或者警告信息。比如为了防止用户误删重要内容，在删除前弹出一个确认对话框。
			还可以设置多选框或者单选框
			 */
			final String[] items=new String[] { "Android", "IOS", "Microsoft" };
			final boolean[] itemsChecked = new boolean [items.length];
			
			final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
			alertDialog.setIcon(R.drawable.ic_launcher);
			alertDialog.setTitle("请选择一项或者多项");
			alertDialog.setCancelable(true);
			alertDialog.setMultiChoiceItems(items,itemsChecked, new DialogInterface.OnMultiChoiceClickListener() {				
						public void onClick(DialogInterface dialog, int which, boolean isChecked) {													
							LogUtil.d("选择项", items[which]);						
							Toast.makeText(getBaseContext(), items[which] + (isChecked ? " checked!":" unchecked!"), 
								Toast.LENGTH_SHORT).show();
						}
					}
				);				
			alertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton)
						{
						}
					}
				);
			alertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,	int whichButton){						
							
				    }
			}
				);
			alertDialog.show();
			
			break;
			
			

		case R.id.date_picker_dialog:
			
			/**
			时间日期对话框经常使用在注册表单中的生日等情况下，需要熟练掌握
			 */	
			final int year,monthOfYear,dayOfMonth,hourOfDay,minute;
			Calendar calendar = Calendar.getInstance();
			year = calendar.get(Calendar.YEAR);
			monthOfYear = calendar.get(Calendar.MONTH);
			dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
			hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
			minute = calendar.get(Calendar.MINUTE);
			
			
			/**
             * 实例化一个DatePickerDialog的对象
             * 第二个参数是一个DatePickerDialog.OnDateSetListener匿名内部类，当用户选择好日期点击done会调用里面的onDateSet方法
             */
			//显示日期对话框，与系统自带的设置日期的格式一样
			DatePickerDialog mDatePicker= new DatePickerDialog(FirstActivity.this,
					new DatePickerDialog.OnDateSetListener()
             {
                 @Override
                 public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth)
                 {
                    Toast.makeText(getBaseContext(), "日期：" + year + "-" + (monthOfYear + 1) 
                    		+ "-" + dayOfMonth, Toast.LENGTH_SHORT).show();
                	 // editText.setText("日期：" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);//通常用于让用户选择之后填到注册表中
                    
                    
                  //显示时间对话框
                    TimePickerDialog mTimePickerDialog=new TimePickerDialog(FirstActivity.this,
                    		new TimePickerDialog.OnTimeSetListener() {					
						@Override
						public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
							Toast.makeText(getBaseContext(), "时间：" + hourOfDay + "：" + minute, Toast.LENGTH_SHORT).show();						
						}
					},hourOfDay,minute,true);
                    mTimePickerDialog.show();
                    
                    
                 }
             }, year, monthOfYear, dayOfMonth);
			mDatePicker.show();

		

			break;
			
			
		case R.id.camera:
			
			/**
			调用系统相机拍照或者从图库选取图片，剪裁之后跳转到第二个活动
			 */
			final AlertDialog.Builder choice = new AlertDialog.Builder(FirstActivity.this);
			choice.setCancelable(true);// 设置是否可以通过点击Back键取消  
			choice.setSingleChoiceItems(new String[] { "拍照", "图库选择"}, -1, new DialogInterface.OnClickListener() {				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if(which==0){
						/**调用系统相机*/
						//Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
						Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						/**
						 *指定图片的存储路径，把该路径通过intent传到系统相机程序中；
						 *指定路径前需要先检查SD卡的状态，再创建或者选择图片的保存目录，最后为图片命名，指定唯一的地址
						 * */
						// 检测SD卡是否可用,涉及到了向 SD 卡中写数据的操作，因此我们还需要在 AndroidManifest.xml中声明权限：
						String sdState = Environment.getExternalStorageState();
						if (!sdState.equals(Environment.MEDIA_MOUNTED)) {
							Toast.makeText(getBaseContext(), "没有存储卡", Toast.LENGTH_LONG).show();
							return;
						}
						
						
						// 创建图片保存目录
						File file = new File(Environment.getExternalStorageDirectory() + "/AAAAA/");//获取根目录
						if (!file.exists()) {//判断文件是否已存在
							file.mkdir();
						}
						
						
						//为图片命名,参数一为文件目录，参数二为图片的名字，以拍照时的时间命名
						File imagePath = new File(file, new SimpleDateFormat("yyyymmdd_hhmmss").format(new Date()) + ".jpg");
						// 将 File 对象转换成 Uri 对象，这个 Uri 对象标识着 这张图片的唯一地址
						imageUri = Uri.fromFile(imagePath);
						// 指定图片的输出地址
						intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
						//拍下的照片将会输出到 imagePath中。
						startActivityForResult(intent, TAKE_PHOTO);
						dialog.dismiss();

					}
					
					if(which==1){
						/**调用系统相册*/
						// Intent intent2=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						// Intent intent2 =new Intent("android.intent.action.GET_CONTENT");
						Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
						intent.setType("image/*");	// 设置查看类型					
						startActivityForResult(intent, SELECT_PHOTO);
						dialog.dismiss();
					}
				}
			});
			
				
			
			
			choice.show();

			break;
			
			
		case R.id.receiver:
			
			/**
			在按钮的点击事件里面发送了一条广播，这条广播就是用于通知程序强制用户下线的，
			强制用户下线的逻辑应该写在接收这条广播的广播接收器里面，这样强制下线的功能就不会依附于任何的界面，
			不管是在程序的任何地方，只需要发出这样一条广播，就可以完成强制下线的操作了。
			 */
			Intent receiverIntent = new Intent("com.example.receiver.FORCE_OFFLINE");
			sendBroadcast(receiverIntent);//点击按钮时发送广播
			break;
			
			
			
		case R.id.notification:
			/**	
			  	创建通知的详细步骤:
			1.首先需要一个 NotificationManager 来对通知进行管理，可以调用 Context 的getSystemService()方法获取到,
				getSystemService()方法接收一个字符串参数用于确定获取系统的哪个服务.
			2.接下来需要创建一个 Notification 对象，这个对象用于存储通知所需的各种信息，我们可以使用它的有参构造函数来进行创建。
				Notification 的有参构造函数接收三个参数，第一个参数用于指定通知的图标，第二个参数用于指定通知的 ticker 内容，
				当通知刚被创建的时候，它会在系统的状态栏一闪而过，属于一种瞬时的提示信息。第三个参数用于指定通知被创建的时间， 以毫秒为单位， 
				当下拉系统状态栏时， 这里指定的时间会显示在相应的通知上。
			3.还需要对通知的布局进行设定，这里只需要调用Notification 的 setLatestEventInfo()方法就可以给通知设置一个标准的布局.
				第四个参数是一个 PendingIntent 对象。这里就可以通过 PendingIntent 构建出一个 延迟执行的“意图” ，当用户点击这条通知时就会执行相应的逻辑。
			4.调用 NotificationManager 的 notify()方法就可以让通知显示出来了。
				notify()方法接收两个参数，第一个参数是 id，要保证为每个通知所指定的 id 都是不同的。
				
			*/
			NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			Notification notification=new Notification(R.drawable.ic_launcher,"通知的ticker内容，瞬时提示信息。",System.currentTimeMillis());
			Intent notificationIntent=new Intent("com.example.activitytest.ACTION_START");
			notificationIntent.addCategory("com.example.activitytest.MY");
			//第二个参数一般用不到，传入0即可；第四个参数用于确定PendingIntent的行为。
			PendingIntent pIntent=PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
			notification.setLatestEventInfo(this, "通知的标题内容", "通知的正文内容", pIntent);
			
			//下标为 0的值表示手机静止的时长，下标为 1的值表示手机振动的时长，下标为 2 的值又表示手机静止的时长，以此类推。
			//想要控制手机振动还需要声明权限的.
			long[] vibrates = {0, 1000, 1000, 1000};
			notification.vibrate = vibrates;
			
			notification.defaults = Notification.DEFAULT_ALL;
			notificationManager.notify(1,notification);
			break;
			
		
			
		case R.id.assets:
			Intent assetsIntent=new Intent(FirstActivity.this,VisitAssetsActivity.class);
			startActivity(assetsIntent);
			break;
			
			
		case R.id.contacts:
			Intent contactsIntent=new Intent(FirstActivity.this,ProviderActivity.class);
			startActivity(contactsIntent);
			break;
			
			
		case R.id.library:
			Intent libIntent=new Intent(FirstActivity.this,AddBookActivity.class);
			startActivity(libIntent);
			break;
			
			
		case R.id.message:
			Intent msgIntent=new Intent(FirstActivity.this,MessageActivity.class);
			startActivity(msgIntent);
			break;
			
		

		default:
			break;
		}
		
	}
	
			
	public boolean onCreateOptionsMenu(Menu menu){//创建菜单
		getMenuInflater().inflate(R.menu.main,menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){//定义菜单的响应事件
		switch (item.getItemId()){
		case R.id.add_item:
			/**
			   更改Toast显示的位置
			 */
			Toast toast=Toast.makeText(getBaseContext()," hacks",Toast.LENGTH_LONG);
			toast.setGravity(Gravity.RIGHT|Gravity.TOP, 30, 200);//偏移距离
			toast.show();
			break;
			
		case R.id.exit_current:
			Intent intent=new Intent(FirstActivity.this,LoginActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		
		return true;
	}
	
	
	protected void onActivityResult(int requestCode,int resultCode,Intent intent ){//请求码；处理结果；携带返回数据的Intent
		switch (requestCode){//检查 requestCode 的值来判断数据来源
		case USE_INTENT:
			if (resultCode== RESULT_OK ){//通过 resultCode 的值来判断处理结果是否成功
				String returnData=intent.getStringExtra("data_return");
				LogUtil.d("data_return", returnData);
				btn2.setText(returnData);
			}
			break;
			
		case TAKE_PHOTO:
			if(resultCode== RESULT_OK ){
			Intent cropiIntent = new Intent("com.android.camera.action.CROP");
			cropiIntent.setDataAndType(imageUri, "image/*");
			cropiIntent.putExtra("scale", true);
			cropiIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			startActivityForResult(cropiIntent, CROP_PHOTO);// 启动裁剪程序
			LogUtil.d("AAA", imageUri.toString());
			}
			break;
			
		case SELECT_PHOTO:
			imageUri = intent.getData();// 获取所选取的图片。
			if(resultCode== RESULT_OK ){
				Intent cropiIntent = new Intent("com.android.camera.action.CROP");
				cropiIntent.setDataAndType(imageUri, "image/*");
				cropiIntent.putExtra("scale", true);// 是否允许缩放和裁剪
				cropiIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);// 图片的输出位置
				startActivityForResult(cropiIntent, CROP_PHOTO);// 启动裁剪程序
				LogUtil.d("AAA", imageUri.toString());
				}
		break;
			
		case CROP_PHOTO:
			if (resultCode== RESULT_OK ) {
			try {
				InputStream is = getContentResolver().openInputStream(imageUri);//输入流
				Bitmap bitmap = BitmapFactory.decodeStream(is);// 把照片解析成 Bitmap 对象
				//Bitmap bitmap = (Bitmap)  intent.getExtras().get("data");// 获取相机返回的数据，并转换为Bitmap图片格式  
				
				//Intent不能直接传递大于40k的图片，所以先把bitmap存储为byte数组，然后再通过Intent传递。			
				ByteArrayOutputStream stream = new ByteArrayOutputStream();//输出流
				// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到bitmap中
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
				int options = 90;
				while (stream.toByteArray().length / 1024 > 200)// 图片大于200KB
				{
					stream.reset();// 重置stream					
					bitmap.compress(CompressFormat.JPEG, options, stream);// 这里压缩为options%					
					options -= 20;// 每次减少20
				}				
				bitmap.recycle();
				bitmap = null;
				
		        byte[] bitmapBytes = stream.toByteArray(); 
		       
				Intent btIntent=new Intent(FirstActivity.this,SecondActivity.class);
				btIntent.putExtra("BMP",bitmapBytes);
				startActivity(btIntent);
				ImageTools.StoreToSD(bitmap, PicName);//把最后的图片保存到sd卡中
				           
				is.close();
				stream.flush();  
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			break;
			
		default:
			break;
		}		
	}

	
}
