package pl.zbieracz2000.recoveryrebooter;

import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

import androidx.annotation.RequiresApi;

import java.io.DataOutputStream;
import java.io.IOException;

@RequiresApi(api = Build.VERSION_CODES.N)
public class recovery extends TileService {
    // Called when the user taps on your tile in an active or inactive state.
    @Override
    public void onStartListening() {
        Tile tile = getQsTile();
        if (tile != null) {
            tile.setState(Tile.STATE_INACTIVE);
            tile.updateTile();
        }
        super.onStartListening();
    }
    public void onClick() {
        super.onClick();
        Process p;
        try {
            // Preform su to get root privledges
            p = Runtime.getRuntime().exec("su");
            // Attempt to write a file to a root-only
            DataOutputStream os = new DataOutputStream(p.getOutputStream());
            os.writeBytes("reboot recovery\n");
            os.writeBytes("exit\n");
            os.flush();
            try {
                p.waitFor();
                if (p.exitValue() != 255) {
                    // TODO Code to run on success
                }
                else {
                    // TODO Code to run on unsuccessful
                }
            } catch (InterruptedException e) {
                // TODO Code to run in interrupted exception
            }
        } catch (IOException e) {
            // TODO Code to run in input/output exception
        }


    }

}