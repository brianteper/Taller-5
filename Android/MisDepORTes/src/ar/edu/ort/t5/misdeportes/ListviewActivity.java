package ar.edu.ort.t5.misdeportes;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

public class ListviewActivity extends Activity{

    ListView listViewPhoneBook;
    Context context;
     @Override
    protected void onCreate(Bundle savedInstanceState)
     {
//             super.onCreate(savedInstanceState);
//             setContentView(R.layout.listview_activity_main);
//             context=this;
//             //get the ListView Reference
//             ListView listViewSMS=(ListView)findViewById(R.id.listViewSMS);
//            
//              //arrayColumns is the column name in your cursor where you're getting the data 
//              // here we are displaying  SMSsender Number i.e. address and SMSBody i.e. body
//              String[] arrayColumns = new String[]{"address","body"};
//              //arrayViewID contains the id of textViews
//              // you can add more Views as per Requirement
//              // textViewSMSSender is connected to "address" of arrayColumns
//              // textViewMessageBody is connected to "body"of arrayColumns
//
//              //              int[] arrayViewIDs = new int[]{R.id.textViewSMSSender,R.id.textViewMessageBody};
//                
//                
//              Cursor cursor;
//              
//                cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
//              
//            // create an Adapter with arguments layoutID, Cursor, Array Of Columns, and Array of ViewIds which is to be Populated
//              
//             SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listview_each_item, cursor, arrayColumns, arrayViewIDs);
//             listViewSMS.setAdapter(adapter);
//            
//            
//            
//             // To handle the click on List View Item
//             listViewSMS.setOnItemClickListener(new OnItemClickListener()
//            {
//               
//                            public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
//                            {
//                                // when user clicks on ListView Item , onItemClick is called
//                                // with position and View of the item which is clicked
//                                // we can use the position parameter to get index of clicked item
////                                TextView textViewSMSSender=(TextView)v.findViewById(R.id.textViewSMSSender);
////                                TextView textViewSMSBody=(TextView)v.findViewById(R.id.textViewMessageBody);
////                                String smsSender=textViewSMSSender.getText().toString();
////                                String smsBody=textViewSMSBody.getText().toString();
//                              
//                                // Show The Dialog with Selected SMS
//                                AlertDialog dialog = new AlertDialog.Builder(context).create();
//                                dialog.setTitle("SMS From : "+smsSender);
//                                dialog.setIcon(android.R.drawable.ic_dialog_info);
//                                dialog.setMessage(smsBody);
//                                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
//                                        new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int which)
//                                    {
//                                  
//                                            dialog.dismiss();
//                                            return;
//                                }  
//                                });
//                                dialog.show();
//                            }
//                           
//                        });
         }
}
