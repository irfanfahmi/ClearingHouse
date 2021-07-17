package com.descolab.chbpip.mitra_mou;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.descolab.chbpip.R;
import com.descolab.chbpip.form_kerjasama.FormKerjasamaActivity;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class PdfViewActivity extends AppCompatActivity {
    ProgressDialog mProgressDialog;
    public Context context;
    PDFView pdfView;
    Toolbar toolbar;
    TextView tvCurrentPage, tvTotalPage;
    Button btnGoPage;
    TextView buttonSaran;
    ImageView btnNext,btnBack,btnIvLabelMitra;
    EditText etPage;
    String idBuku;
    public String samplePDF = "";
    public String namePDF = "";
    public String baseURL = "";
    public static final int PERMISSIONS_MULTIPLE_REQUEST_CERTIFICATE = 123;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
        context = PdfViewActivity.this;
        Intent intent = getIntent();
        String linkFile = intent.getStringExtra("link_file");
        String titleFile = intent.getStringExtra("title");
        String uriFile = intent.getStringExtra("file");
        String namaBuku = intent.getStringExtra("nama_buku");

        Log.d("Get Data PDF","Link nama buku : "+linkFile);

        samplePDF = uriFile;
        namePDF = titleFile;
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.show();
        mProgressDialog.setMessage("Sedang disiapkan...");
        mProgressDialog.setCancelable(false);

        checkAndroidVersionCertificate();
        pdfView =  findViewById(R.id.pdfView);
        btnNext = findViewById(R.id.next);
        btnBack = findViewById(R.id.back);
        buttonSaran = findViewById(R.id.btnSaran);
        btnIvLabelMitra = findViewById(R.id.ivLabelMitra);
        btnGoPage = findViewById(R.id.klik);
        etPage = findViewById(R.id.etPage);
        btnIvLabelMitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        buttonSaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FormKerjasamaActivity.class);
                i.putExtra("saran","3");
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    private void downloadFile() {
        mProgressDialog.show();
        mProgressDialog.setMessage("Sedang disiapkan...");
//        mProgressDialog.setMax(100);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setCancelable(true);
        Log.d("Cek Download","Link : "+samplePDF);
        DownloadFileTask task = new DownloadFileTask( PdfViewActivity.this,  samplePDF,  "/download/"+namePDF);
        task.startTask();
    }


    public class DownloadFileTask {
        public static final String TAG = "DownloadFileTask";
        private PdfViewActivity context;
        private GetTask contentTask;
        private String url;
        private String fileName;
        public DownloadFileTask(PdfViewActivity context, String url, String fileName) {
            this.context = context;
            this.url = url;
            this.fileName = fileName;
        }
        public void startTask() {
            doRequest();
        }
        private void doRequest() {
            contentTask = new GetTask();
            contentTask.execute();
        }
        private class GetTask extends AsyncTask< String, Integer, String > {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mProgressDialog.show();
                mProgressDialog.setMessage("Sedang disiapkan...");

            }

            @Override protected String doInBackground(String... strings) {

                int count;
                try {
                    URL _url = new URL(url);
                    URLConnection conection = _url.openConnection();
                    conection.connect();
                    String extension = url.substring(url.lastIndexOf('.') + 1).trim();
                    InputStream input = new BufferedInputStream(_url.openStream(),  8192);
                    OutputStream output = new FileOutputStream( Environment.getExternalStorageDirectory() + fileName);
                    byte data[] = new byte[1024];
                    while ((count = input.read(data))  != -1) {
                        output.write(data, 0, count);
                    }
                    output.flush();
                    output.close();
                    input.close();
                } catch (Exception e) {
                    Log.e("Error: ", e.getMessage());
                }
                return null;
            }


            protected void onPostExecute(String data) {
                context.onFileDownloaded();
            }
        }
    }
    public void onFileDownloaded() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }


        File file = new File(Environment.getExternalStorageDirectory() .getAbsolutePath()  + "/download/"+namePDF);
        if (file.exists()) {
            etPage.setText(String.valueOf(pdfView.getCurrentPage()));
            pdfView.fromFile(file)  //.pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                    .enableSwipe(true)
                    .swipeHorizontal(true)
                    .autoSpacing(true)
                    .pageSnap(true)
                    .pageFling(true)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .enableAnnotationRendering(true)
                    .password(null)
                    .scrollHandle(null)
                    .onLoad(new OnLoadCompleteListener() {
                @Override  public void loadComplete(int nbPages) {

                }
            }) .load();
            btnGoPage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pdfView.jumpTo(Integer.parseInt(etPage.getText().toString()) - 1);
                }
            });

            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pdfView.jumpTo(pdfView.getCurrentPage()-1);
                }
            });
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pdfView.jumpTo(pdfView.getCurrentPage()+1);

                }
            });
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)  private void checkPermission_Certificate() {
        if (ContextCompat.checkSelfPermission(context,  Manifest.permission.READ_EXTERNAL_STORAGE) + ContextCompat .checkSelfPermission(context,  Manifest.permission.WRITE_EXTERNAL_STORAGE)  != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale ((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE) ||  ActivityCompat.shouldShowRequestPermissionRationale ((Activity) context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                requestPermissions( new String[] {
                        Manifest.permission .READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
                },  PERMISSIONS_MULTIPLE_REQUEST_CERTIFICATE);
            } else {
                requestPermissions( new String[] {
                        Manifest.permission .READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
                },  PERMISSIONS_MULTIPLE_REQUEST_CERTIFICATE);
            }
        } else {  // write your logic code if permission already granted
            if (!samplePDF.equalsIgnoreCase("")) {
                downloadFile();
            }
        }
    }
    private void checkAndroidVersionCertificate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission_Certificate();
        } else {
            if (!samplePDF.equalsIgnoreCase("")) {
                downloadFile();
            }
        }
    }
    @Override  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_MULTIPLE_REQUEST_CERTIFICATE:
                if (grantResults.length > 0) {
                    boolean writePermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean readExternalFile = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (writePermission && readExternalFile) {
                        if (!samplePDF.equalsIgnoreCase("")) {
                            downloadFile();
                        }
                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (Build.VERSION.SDK_INT >= 23 &&  !shouldShowRequestPermissionRationale(permissions[0])) {
                            Intent intent = new Intent();
                            intent.setAction(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                        } else {
                            requestPermissions( new String[] {
                                    Manifest.permission .READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
                            },  PERMISSIONS_MULTIPLE_REQUEST_CERTIFICATE);
                        }
                    }
                }
                break;
        }
    }
}
