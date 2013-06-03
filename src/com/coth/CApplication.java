package com.coth;

import android.app.Application;
import com.coth.ui.HomeScreenActivity;
import com.coth.utils.ImageDownloader;
import com.coth.webservice.CothWebService;

/**
 * Created by yanga on 2013/05/26.
 */
public class CApplication  extends Application{

    private CothWebService cws;
    private ImageDownloader imageManager;
    @Override
    public void onCreate() {
        setImageManager(new ImageDownloader());
        setCws(new CothWebService(getApplicationContext()));
        super.onCreate();
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        setImageManager(null);
        setCws(null);
    }
    public CothWebService getCws() {
        return cws;
    }
    public ImageDownloader getImageManager() {
        return imageManager;
    }
    private void setCws(CothWebService cws) {
        this.cws = cws;
    }
    private void setImageManager(ImageDownloader id) {
        this.imageManager = id;
    }
}
