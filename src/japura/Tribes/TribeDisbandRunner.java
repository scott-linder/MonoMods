/**
 *      author: Monofuel
 *      website: japura.net
 *      this file is distributed under the modified BSD license
 *      that should have been included with it.
 */

package japura.Tribes;

import japura.MonoUtil.MonoConf;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.json.simple.JSONObject;

public class TribeDisbandRunner extends BukkitRunnable{

	private final JavaPlugin plugin;
	long timeDeltaInMillis = 0;
	
	public TribeDisbandRunner(JavaPlugin plugin) {
		this.plugin = plugin;
		//1000 milliseconds in a second
		//60 seconds in a minute
		//60 minutes in an hour
		//24 hours in a day
		timeDeltaInMillis = 1000 * 60 * 60 * 24 * (long) Tribes.getConf().getConf("Days before disband");
		Tribes.log("Tribe Disband spawned");
		
	}
	
	public void run() {
		Tribe[] currentTribes = Tribes.getTribes();
		long currentTime = System.currentTimeMillis();
		for (Tribe item : currentTribes) {
			if (currentTime - item.getLastLogTime() > timeDeltaInMillis) {
				Tribes.log("Tribe " + item.getName() + " has exceeded the time since last login limit");
				Tribes.destroyTribe(item);
			}

		}
	}
}
