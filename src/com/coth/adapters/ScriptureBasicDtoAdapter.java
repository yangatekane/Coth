package com.coth.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.coth.CApplication;
import com.coth.ui.R;
import com.coth.utils.ImageDownloader;
import com.coth.webservice.objects.ScriptureBasicDto;

import java.util.List;

/**
 * Created by yanga on 2013/05/25.
 */
public class ScriptureBasicDtoAdapter extends BaseAdapter implements View.OnClickListener {

    public final int TEMPLATE_DEFAULT = 1;
    public int currentTemplate;
    private List<ScriptureBasicDto> scriptures;
    private Context context;

    public ScriptureBasicDtoAdapter(Context context,List<ScriptureBasicDto> scriptures ){
        this.context = context;
        this.scriptures = scriptures;
        this.currentTemplate = TEMPLATE_DEFAULT;
    }

    public void setScripture(List<ScriptureBasicDto> scriptures){
        this.scriptures = scriptures;
    }

    @Override
    public int getCount() {
        return scriptures.size();
    }

    @Override
    public Object getItem(int i) {
        return scriptures.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ScriptureBasicDto scripture = scriptures.get(i);
        convertView = getViewForTemplate(scripture, convertView, currentTemplate);
        return convertView;
    }

    @Override
    public void onClick(View view) {

    }
    private View getViewForTemplate(ScriptureBasicDto scripture, View convertView, int templateType) {


        switch (templateType) {
            case TEMPLATE_DEFAULT: {
                DefaultTemplateViewHolder holder = new DefaultTemplateViewHolder();
                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.scripture_basic_screen_adto, null);

                    //holder.txtTitle = (TextView) convertView.findViewById(R.id.);
                    holder.txtDescription = (TextView) convertView.findViewById(R.id.basicScriptureScreen_txtDescription);
                    holder.imgAvatar = (ImageView) convertView.findViewById(R.id.basicScriptureScreen_imgAvatar);
                    holder.btnAvailable = (Button) convertView.findViewById(R.id.btnAvailable);
                    holder.btnViewProfile = (Button) convertView.findViewById(R.id.btnViewProfile);
                    holder.name = (TextView) convertView.findViewById(R.id.basicScriptureScreen_name);

                    convertView.setTag(holder);
                } else {
                    holder = (DefaultTemplateViewHolder) convertView.getTag();
                }

                //holder.txtTitle.setText(scripture.FistName);
                holder.txtDescription.setText(scripture.description);
                holder.name.setText(scripture.title+" "+scripture.last_name);
                holder.imgAvatar.setImageResource(R.drawable.profile_avatar_blank);
                //CApplication mApplication = (CApplication) context.getApplicationContext();
                //ImageDownloader imageManager = mApplication.getImageManager();
                //imageManager.download(expert.ThumbnailFileName, holder.imgAvatar);
                //Integer mThumbIds = R.drawable.pastor;
                holder.imgAvatar.setImageResource(scripture.imgAvatar);
                holder.btnAvailable.setTag(scripture);
                holder.btnViewProfile.setTag(scripture);

                holder.btnViewProfile.setBackgroundResource(R.drawable.button_blue);
                //disable the call now button if the expert is not available

                if (!scripture.IsOnline && !scripture.IsBusy) {
                    holder.btnAvailable.setEnabled(false);
                    holder.btnAvailable.setText("Sleeping");
                    holder.btnAvailable.setBackgroundResource(R.drawable.button_red);

                } else if (scripture.IsBusy) {
                    holder.btnAvailable.setEnabled(false);
                    holder.btnAvailable.setText("Driving");
                    holder.btnAvailable.setBackgroundResource(R.drawable.button_orange);

                } else {
                    holder.btnAvailable.setEnabled(false);
                    holder.btnAvailable.setText("Available");
                    holder.btnAvailable.setBackgroundResource(R.drawable.button_green);

                }

            }
            break;
            default:
                break;
        }
        return convertView;

    }

    static class DefaultTemplateViewHolder {
        //TextView txtTitle;
        TextView txtDescription;
        TextView name;
        ImageView imgAvatar;
        Button btnAvailable;
        Button btnViewProfile;
    }

}
