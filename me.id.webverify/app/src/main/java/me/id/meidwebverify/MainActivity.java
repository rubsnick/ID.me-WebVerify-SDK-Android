package me.id.meidwebverify;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import me.id.webverifylib.IDmeWebVerify;


public class MainActivity extends ActionBarActivity
{
    private IDmeWebVerify webVerify;
    private String clientID = null;
    private String redirectUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnVerify = (Button) findViewById(R.id.btnVerify);

        btnVerify.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
            {
                Verify();
            }
        });
    }

    /**
     * Method that Starts the Verification Process.
     */
    public void Verify()
    {
        Spinner spnRoute = (Spinner) findViewById(R.id.spnRoute);
        String affiliationType = "";
        String url = "";

        if (spnRoute.getSelectedItem().toString().equals("Military"))
        {
            affiliationType = IDmeWebVerify.MILITARY;
        }
        else if (spnRoute.getSelectedItem().toString().equals("Student"))
        {
            affiliationType = IDmeWebVerify.STUDENT;
        }
        else if (spnRoute.getSelectedItem().toString().equals("Teacher"))
        {
            affiliationType = IDmeWebVerify.TEACHER;
        }
        else if (spnRoute.getSelectedItem().toString().equals("First Responder"))
        {
            affiliationType = IDmeWebVerify.FIRST_RESPONDER;
        }

        webVerify = new IDmeWebVerify(clientID, redirectUri, affiliationType, this);
        webVerify.StartWebView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            if (requestCode == IDmeWebVerify.WEB_REQUEST_CODE)
            {
                String response = data.getStringExtra(IDmeWebVerify.IDME_WEB_VERIFY_RESPONSE);

                TextView txtResult = (TextView) findViewById(R.id.txtResult);
                txtResult.setText("Response : " + response);
            }
        }

    }
}
