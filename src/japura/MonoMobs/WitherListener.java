/**
 *      author: Monofuel
 *      website: japura.net
 *      this file is distributed under the modified BSD license
 *      that should have been included with it.
 */


package japura.MonoMobs;


import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class WitherListener implements Listener{
	
	public WitherListener(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this,plugin);
	}
	
	@EventHandler
	public void onMobSpawn(CreatureSpawnEvent event) {
		LivingEntity mob = event.getEntity();
		
		if (mob instanceof Wither) {
			
			event.setCancelled(true);
			MonoMobs.log("Wither spawn averted");
		}
	}
	
	
}
