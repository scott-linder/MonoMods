/**
 *      author: Monofuel
 *      website: japura.net
 *      this file is distributed under the modified BSD license
 *      that should have been included with it.
 */


package japura.NoWither;

import japura.MonoUtil.MonoConf;

import org.json.simple.JSONObject;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class NoWither extends JavaPlugin{
	
	private static Logger WitherLogger = null;
	
	private static MonoConf config = null;
	private static final String configLoc = "plugins/NoWither";

	public JSONObject genDefaultConf() {
		JSONObject defaults = new JSONObject();

		//this is where default config settings go
		defaults.put("wither disabled",true);
		
		return defaults;
	}

	public void onEnable() {
		WitherLogger = getLogger();
		
		new WitherListener(this);
		
		//load configuration
		config = new MonoConf(this,genDefaultConf());
		
		log("NoWither has been enabled");
		
		//check if we want wither disabled, and if so, disable
		if((boolean) config.getConf("wither disabled")) {
			//create anti-wither listener
			log("wither disabled");
			new WitherListener(this);
		} else {
			log("wither enabled");
		}
	}
	
	public void onDisable() {
		
		
		//write config back out to file
		//if there were no errors reading config in
		config.close();
		
		log("NoWither has been disabled");
		WitherLogger = null;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("NoWither")) {
			if (args[0].equalsIgnoreCase("reload")) {
				this.getServer().getPluginManager().disablePlugin(this);
				this.getServer().getPluginManager().enablePlugin(this);
				return true;
			} else if (args[0].equalsIgnoreCase("unload")) {
				this.getServer().getPluginManager().disablePlugin(this);
				return true;
			} else if (args[0].equalsIgnoreCase("load")) {
				//GARBAGE EVERYWHERE
				config = new MonoConf(this,genDefaultConf());
				return true;
			} else if (args[0].equalsIgnoreCase("save")) {
				config.close();
				config = new MonoConf(this,genDefaultConf());
				return true;
			} else if (args[0].equalsIgnoreCase("help")) {
				String help = "NoWither is configured from the config.\n" +
							"/nowither load will reload the config.";
				sender.sendMessage(help);
				
				return true;
			}

		}
		
		return false;
	}
	
	//let other objects call our logger
	public static void log(String line) {
		WitherLogger.info(line);
	}
}
