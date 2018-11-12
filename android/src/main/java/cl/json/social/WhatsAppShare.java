package cl.json.social;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.ComponentName;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;

/**
 * Created by disenodosbbcl on 23-07-16.
 */
public class WhatsAppShare extends SingleShareIntent {

    private static final String PACKAGE = "com.whatsapp";
    private static final String PLAY_STORE_LINK = "market://details?id=com.whatsapp";

    public WhatsAppShare(ReactApplicationContext reactContext) {
        super(reactContext);
    }
    @Override
    public void open(ReadableMap options) throws ActivityNotFoundException {
        super.open(options);
        //  extra params here
        if (WhatsAppShare.hasValidKey("wa_number", options)) {
            this.getIntent().setAction(Intent.ACTION_SEND);
            this.getIntent().setComponent(new ComponentName("com.whatsapp","com.whatsapp.ContactPicker"));
            this.getIntent().putExtra("jid", options.getString("wa_number") + "@s.whatsapp.net");
        }

        this.openIntentChooser();
    }
    @Override
    protected String getPackage() {
        return PACKAGE;
    }

    @Override
    protected String getDefaultWebLink() {
        return null;
    }

    @Override
    protected String getPlayStoreLink() {
        return PLAY_STORE_LINK;
    }
}
