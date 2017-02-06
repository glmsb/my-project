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
	//ʹ��ʵ�ֽӿڷ�ʽע���������������ť�ĵ���¼�
	public void onClick(View v) {
		switch (v.getId()) { //��ȡ��ť��id

		case R.id.button_1:
			
			/**
			 * ��ʽintent���������ֵ��ȥ
			 */
			String data1="�2��Ҫ�Ĳ���1";
			String data2="�2��Ҫ�Ĳ���2";
			//�2�ж���ķ���
			SecondActivity.actionStart(FirstActivity.this, data1, data2);
	
			break;
					
			
		case R.id.button_2:
			
			/**
			ͨ����ʽ Intent�������,����ֵ������
			 */
			Intent intent=new Intent("com.example.activitytest.ACTION_START");
			intent.addCategory("com.example.activitytest.MY");
			startActivityForResult(intent, USE_INTENT);
			
			break;
			
			
			/**
			 ʹ����ʽ Intent�����ǲ������������Լ������ڵĻ��������������������Ļ��
			 ��ʹ�� Android���Ӧ�ó���֮��Ĺ��ܹ����Ϊ�˿���
			 */
		case R.id.button_3:
			
			/**
			����ϵͳ����������������ҳ
			 */	
			String url="http://www.baidu.com";
			Intent intent1=new Intent(Intent.ACTION_VIEW);// Android ϵͳ���õĶ���
			intent1.putExtra("url", url);
			intent1.setData(Uri.parse(url));//����ָ����ǰ Intent���ڲ���������
			startActivity(intent1);

			break;
			
			
		case R.id.button_4:
			
			/**
			����ϵͳ���Ž���
			Intent.ACTION_CALL,��Ҫ����Ȩ�ޣ�ֱ�ӵ���ϵͳ����
			 */

			Intent intent2=new Intent(Intent.ACTION_DIAL);
			intent2.setData(Uri.parse("tel:18559650956"));
			startActivity(intent2);
			break;
			
			
		case R.id.progress_dialog:
			
			/**
			 * �ڶԻ�������ʾһ��Բ�ν��������������������ؼ�,
			 * ʹ�þ�̬��ʽ��������ʾ�����ֽ�����ֻ����Բ����,����title��Message��ʾ���� ,
			 * �������һ������boolean indeterminate�����Ƿ��ǲ���ȷ��״̬
			 */			
			final ProgressDialog dialog = ProgressDialog.show(this,  "��ʾ", "���ڵ�½��.....", false);
			//���崦����Ϣ�Ķ���  
			  final   Handler handler = new Handler(new Handler.Callback() {  
		        //���봫��Ϣ���˾���ôô��  
 			public boolean handleMessage(Message msg){  
		        	dialog.dismiss();//�Ի�����ʧ  
		            Toast.makeText(getBaseContext(), "��½�ɹ�", Toast.LENGTH_LONG).show();
					return false;     
		        }  
		    });  
		    
			new Thread(new Runnable() {			
				@Override
				public void run() {
					try {
						//�����ʱ������������������
						Thread.sleep(10000);//ģ���̨����
						
						// cancel��dismiss�������ʶ���һ���ģ����Ǵ���Ļ��ɾ��Dialog,Ψһ��������  
	                    // ����cancel������ص�DialogInterface.OnCancelListener���ע��Ļ�,dismiss��������ص�  
						//dialog.cancel();
						//dialog.dismiss();
					
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					handler.sendEmptyMessage(0);//������Ϣ  
				}
			}).start();
			
			
			
			/**
			 * �ڶԻ�������ʾһ��ˮƽ���������������������ؼ�
			 */
			final ProgressDialog progressDialog=new ProgressDialog(this);//������һ�� ProgressDialog ����
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// ����ˮƽ������  
			progressDialog.setIcon(R.drawable.ic_launcher);//����title��ͼ�꣬Ĭ����û�еģ����û������title�Ļ�ֻ����Icon�ǲ�����ʾͼ���  
			progressDialog.setTitle("Ӧ��������....");
			progressDialog.setMessage("���Ե�Ƭ�̣�������ƴ�����أ���");
			progressDialog.setCancelable(false);// �����Ƿ����ͨ�����Back��ȡ��  
			progressDialog.setCanceledOnTouchOutside(false);// �����ڵ��Dialog���Ƿ�ȡ��Dialog������ 
			progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"ȡ��", new DialogInterface.OnClickListener() {				
				@Override
				public void onClick(DialogInterface dialog, int whichButton) {
					// TODO Auto-generated method stub					
				}
			});
			
			progressDialog.setButton(DialogInterface.BUTTON_POSITIVE,"��ͣ", new DialogInterface.OnClickListener() {				
				@Override
				public void onClick(DialogInterface dialog, int whichButton) {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(3000);
						progressDialog.getButton(whichButton).setText("����");
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
					    	//�����ʱ������������������
					        Thread.sleep(1000);
					        
					       // ���½������Ľ���,���������߳��и��½���������
					        progressDialog.incrementProgressBy((int)(100/5));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}    
					}
					//�����ݼ�����ɺ����Ҫ���� ProgressDialog��dismiss()�������رնԻ���
					 progressDialog.dismiss();
					   
					    
				}
			}).start();
	
			break;
					
			
			
		case R.id.alert_dialog:
			
			/**
			������ʾһЩ�ǳ���Ҫ�����ݻ��߾�����Ϣ������Ϊ�˷�ֹ�û���ɾ��Ҫ���ݣ���ɾ��ǰ����һ��ȷ�϶Ի���
			���������ö�ѡ����ߵ�ѡ��
			 */
			final String[] items=new String[] { "Android", "IOS", "Microsoft" };
			final boolean[] itemsChecked = new boolean [items.length];
			
			final AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
			alertDialog.setIcon(R.drawable.ic_launcher);
			alertDialog.setTitle("��ѡ��һ����߶���");
			alertDialog.setCancelable(true);
			alertDialog.setMultiChoiceItems(items,itemsChecked, new DialogInterface.OnMultiChoiceClickListener() {				
						public void onClick(DialogInterface dialog, int which, boolean isChecked) {													
							LogUtil.d("ѡ����", items[which]);						
							Toast.makeText(getBaseContext(), items[which] + (isChecked ? " checked!":" unchecked!"), 
								Toast.LENGTH_SHORT).show();
						}
					}
				);				
			alertDialog.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton)
						{
						}
					}
				);
			alertDialog.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,	int whichButton){						
							
				    }
			}
				);
			alertDialog.show();
			
			break;
			
			

		case R.id.date_picker_dialog:
			
			/**
			ʱ�����ڶԻ��򾭳�ʹ����ע����е����յ�����£���Ҫ��������
			 */	
			final int year,monthOfYear,dayOfMonth,hourOfDay,minute;
			Calendar calendar = Calendar.getInstance();
			year = calendar.get(Calendar.YEAR);
			monthOfYear = calendar.get(Calendar.MONTH);
			dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
			hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
			minute = calendar.get(Calendar.MINUTE);
			
			
			/**
             * ʵ����һ��DatePickerDialog�Ķ���
             * �ڶ���������һ��DatePickerDialog.OnDateSetListener�����ڲ��࣬���û�ѡ������ڵ��done����������onDateSet����
             */
			//��ʾ���ڶԻ�����ϵͳ�Դ����������ڵĸ�ʽһ��
			DatePickerDialog mDatePicker= new DatePickerDialog(FirstActivity.this,
					new DatePickerDialog.OnDateSetListener()
             {
                 @Override
                 public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth)
                 {
                    Toast.makeText(getBaseContext(), "���ڣ�" + year + "-" + (monthOfYear + 1) 
                    		+ "-" + dayOfMonth, Toast.LENGTH_SHORT).show();
                	 // editText.setText("���ڣ�" + year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);//ͨ���������û�ѡ��֮���ע�����
                    
                    
                  //��ʾʱ��Ի���
                    TimePickerDialog mTimePickerDialog=new TimePickerDialog(FirstActivity.this,
                    		new TimePickerDialog.OnTimeSetListener() {					
						@Override
						public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
							Toast.makeText(getBaseContext(), "ʱ�䣺" + hourOfDay + "��" + minute, Toast.LENGTH_SHORT).show();						
						}
					},hourOfDay,minute,true);
                    mTimePickerDialog.show();
                    
                    
                 }
             }, year, monthOfYear, dayOfMonth);
			mDatePicker.show();

		

			break;
			
			
		case R.id.camera:
			
			/**
			����ϵͳ������ջ��ߴ�ͼ��ѡȡͼƬ������֮����ת���ڶ����
			 */
			final AlertDialog.Builder choice = new AlertDialog.Builder(FirstActivity.this);
			choice.setCancelable(true);// �����Ƿ����ͨ�����Back��ȡ��  
			choice.setSingleChoiceItems(new String[] { "����", "ͼ��ѡ��"}, -1, new DialogInterface.OnClickListener() {				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if(which==0){
						/**����ϵͳ���*/
						//Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
						Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						/**
						 *ָ��ͼƬ�Ĵ洢·�����Ѹ�·��ͨ��intent����ϵͳ��������У�
						 *ָ��·��ǰ��Ҫ�ȼ��SD����״̬���ٴ�������ѡ��ͼƬ�ı���Ŀ¼�����ΪͼƬ������ָ��Ψһ�ĵ�ַ
						 * */
						// ���SD���Ƿ����,�漰������ SD ����д���ݵĲ�����������ǻ���Ҫ�� AndroidManifest.xml������Ȩ�ޣ�
						String sdState = Environment.getExternalStorageState();
						if (!sdState.equals(Environment.MEDIA_MOUNTED)) {
							Toast.makeText(getBaseContext(), "û�д洢��", Toast.LENGTH_LONG).show();
							return;
						}
						
						
						// ����ͼƬ����Ŀ¼
						File file = new File(Environment.getExternalStorageDirectory() + "/AAAAA/");//��ȡ��Ŀ¼
						if (!file.exists()) {//�ж��ļ��Ƿ��Ѵ���
							file.mkdir();
						}
						
						
						//ΪͼƬ����,����һΪ�ļ�Ŀ¼��������ΪͼƬ�����֣�������ʱ��ʱ������
						File imagePath = new File(file, new SimpleDateFormat("yyyymmdd_hhmmss").format(new Date()) + ".jpg");
						// �� File ����ת���� Uri ������� Uri �����ʶ�� ����ͼƬ��Ψһ��ַ
						imageUri = Uri.fromFile(imagePath);
						// ָ��ͼƬ�������ַ
						intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
						//���µ���Ƭ��������� imagePath�С�
						startActivityForResult(intent, TAKE_PHOTO);
						dialog.dismiss();

					}
					
					if(which==1){
						/**����ϵͳ���*/
						// Intent intent2=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						// Intent intent2 =new Intent("android.intent.action.GET_CONTENT");
						Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
						intent.setType("image/*");	// ���ò鿴����					
						startActivityForResult(intent, SELECT_PHOTO);
						dialog.dismiss();
					}
				}
			});
			
				
			
			
			choice.show();

			break;
			
			
		case R.id.receiver:
			
			/**
			�ڰ�ť�ĵ���¼����淢����һ���㲥�������㲥��������֪ͨ����ǿ���û����ߵģ�
			ǿ���û����ߵ��߼�Ӧ��д�ڽ��������㲥�Ĺ㲥���������棬����ǿ�����ߵĹ��ܾͲ����������κεĽ��棬
			�������ڳ�����κεط���ֻ��Ҫ��������һ���㲥���Ϳ������ǿ�����ߵĲ����ˡ�
			 */
			Intent receiverIntent = new Intent("com.example.receiver.FORCE_OFFLINE");
			sendBroadcast(receiverIntent);//�����ťʱ���͹㲥
			break;
			
			
			
		case R.id.notification:
			/**	
			  	����֪ͨ����ϸ����:
			1.������Ҫһ�� NotificationManager ����֪ͨ���й������Ե��� Context ��getSystemService()������ȡ��,
				getSystemService()��������һ���ַ�����������ȷ����ȡϵͳ���ĸ�����.
			2.��������Ҫ����һ�� Notification ��������������ڴ洢֪ͨ����ĸ�����Ϣ�����ǿ���ʹ�������вι��캯�������д�����
				Notification ���вι��캯������������������һ����������ָ��֪ͨ��ͼ�꣬�ڶ�����������ָ��֪ͨ�� ticker ���ݣ�
				��֪ͨ�ձ�������ʱ��������ϵͳ��״̬��һ������������һ��˲ʱ����ʾ��Ϣ����������������ָ��֪ͨ��������ʱ�䣬 �Ժ���Ϊ��λ�� 
				������ϵͳ״̬��ʱ�� ����ָ����ʱ�����ʾ����Ӧ��֪ͨ�ϡ�
			3.����Ҫ��֪ͨ�Ĳ��ֽ����趨������ֻ��Ҫ����Notification �� setLatestEventInfo()�����Ϳ��Ը�֪ͨ����һ����׼�Ĳ���.
				���ĸ�������һ�� PendingIntent ��������Ϳ���ͨ�� PendingIntent ������һ�� �ӳ�ִ�еġ���ͼ�� �����û��������֪ͨʱ�ͻ�ִ����Ӧ���߼���
			4.���� NotificationManager �� notify()�����Ϳ�����֪ͨ��ʾ�����ˡ�
				notify()��������������������һ�������� id��Ҫ��֤Ϊÿ��֪ͨ��ָ���� id ���ǲ�ͬ�ġ�
				
			*/
			NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			Notification notification=new Notification(R.drawable.ic_launcher,"֪ͨ��ticker���ݣ�˲ʱ��ʾ��Ϣ��",System.currentTimeMillis());
			Intent notificationIntent=new Intent("com.example.activitytest.ACTION_START");
			notificationIntent.addCategory("com.example.activitytest.MY");
			//�ڶ�������һ���ò���������0���ɣ����ĸ���������ȷ��PendingIntent����Ϊ��
			PendingIntent pIntent=PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
			notification.setLatestEventInfo(this, "֪ͨ�ı�������", "֪ͨ����������", pIntent);
			
			//�±�Ϊ 0��ֵ��ʾ�ֻ���ֹ��ʱ�����±�Ϊ 1��ֵ��ʾ�ֻ��񶯵�ʱ�����±�Ϊ 2 ��ֵ�ֱ�ʾ�ֻ���ֹ��ʱ�����Դ����ơ�
			//��Ҫ�����ֻ��񶯻���Ҫ����Ȩ�޵�.
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
	
			
	public boolean onCreateOptionsMenu(Menu menu){//�����˵�
		getMenuInflater().inflate(R.menu.main,menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item){//����˵�����Ӧ�¼�
		switch (item.getItemId()){
		case R.id.add_item:
			/**
			   ����Toast��ʾ��λ��
			 */
			Toast toast=Toast.makeText(getBaseContext()," hacks",Toast.LENGTH_LONG);
			toast.setGravity(Gravity.RIGHT|Gravity.TOP, 30, 200);//ƫ�ƾ���
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
	
	
	protected void onActivityResult(int requestCode,int resultCode,Intent intent ){//�����룻��������Я���������ݵ�Intent
		switch (requestCode){//��� requestCode ��ֵ���ж�������Դ
		case USE_INTENT:
			if (resultCode== RESULT_OK ){//ͨ�� resultCode ��ֵ���жϴ������Ƿ�ɹ�
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
			startActivityForResult(cropiIntent, CROP_PHOTO);// �����ü�����
			LogUtil.d("AAA", imageUri.toString());
			}
			break;
			
		case SELECT_PHOTO:
			imageUri = intent.getData();// ��ȡ��ѡȡ��ͼƬ��
			if(resultCode== RESULT_OK ){
				Intent cropiIntent = new Intent("com.android.camera.action.CROP");
				cropiIntent.setDataAndType(imageUri, "image/*");
				cropiIntent.putExtra("scale", true);// �Ƿ��������źͲü�
				cropiIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);// ͼƬ�����λ��
				startActivityForResult(cropiIntent, CROP_PHOTO);// �����ü�����
				LogUtil.d("AAA", imageUri.toString());
				}
		break;
			
		case CROP_PHOTO:
			if (resultCode== RESULT_OK ) {
			try {
				InputStream is = getContentResolver().openInputStream(imageUri);//������
				Bitmap bitmap = BitmapFactory.decodeStream(is);// ����Ƭ������ Bitmap ����
				//Bitmap bitmap = (Bitmap)  intent.getExtras().get("data");// ��ȡ������ص����ݣ���ת��ΪBitmapͼƬ��ʽ  
				
				//Intent����ֱ�Ӵ��ݴ���40k��ͼƬ�������Ȱ�bitmap�洢Ϊbyte���飬Ȼ����ͨ��Intent���ݡ�			
				ByteArrayOutputStream stream = new ByteArrayOutputStream();//�����
				// ����ѹ������������100��ʾ��ѹ������ѹ��������ݴ�ŵ�bitmap��
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
				int options = 90;
				while (stream.toByteArray().length / 1024 > 200)// ͼƬ����200KB
				{
					stream.reset();// ����stream					
					bitmap.compress(CompressFormat.JPEG, options, stream);// ����ѹ��Ϊoptions%					
					options -= 20;// ÿ�μ���20
				}				
				bitmap.recycle();
				bitmap = null;
				
		        byte[] bitmapBytes = stream.toByteArray(); 
		       
				Intent btIntent=new Intent(FirstActivity.this,SecondActivity.class);
				btIntent.putExtra("BMP",bitmapBytes);
				startActivity(btIntent);
				ImageTools.StoreToSD(bitmap, PicName);//������ͼƬ���浽sd����
				           
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
