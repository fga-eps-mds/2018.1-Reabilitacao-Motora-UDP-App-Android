package com.eps.mds.reabilitacaomotora.controller;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.ScrollView;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_Client {
    private AsyncTask<Void, Void, Void> async_cient;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    @SuppressLint({"NewApi", "StaticFieldLeak"})
    public void sendMessage(final String ipAddress, final Integer port)
    {
        async_cient = new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected Void doInBackground(Void... params)
            {
                DatagramSocket ds = null;

                try
                {
                    ds = new DatagramSocket();
                    DatagramPacket dp;
                    InetAddress ip = InetAddress.getByName(ipAddress);
                    dp = new DatagramPacket(message.getBytes(), message.length(), ip, port);
                    ds.setBroadcast(true);
                    ds.send(dp);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    if (ds != null)
                    {
                        ds.close();
                    }
                }
                return null;
            }

            protected void onPostExecute(Void result)
            {
                super.onPostExecute(result);
            }
        };

        if (Build.VERSION.SDK_INT >= 11) async_cient.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        else async_cient.execute();
    }
}
