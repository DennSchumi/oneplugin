//package dev.unowly.oneplugin.listener.chunkloader;
//
//import org.bukkit.Location;
//import org.bukkit.Material;
//import org.bukkit.block.Beacon;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.block.BlockPlaceEvent;
//
//public class Chunkloader implements Listener {
//
//    @EventHandler
//    public void onBlockPlace(BlockPlaceEvent event){
//        if (event.getBlockPlaced().getType() == Material.BEACON) {
//            Beacon beacon = (Beacon) event.getBlockPlaced().getState();
//            Location location = beacon.getLocation();
//
//            loadChunksAroundBeacon(location, 3);
//            event.getPlayer().sendMessage("§cChunks werden im Wirkungsbereich geladen!");
//        }
//
//
//    }
//
//    public void loadChunksAroundBeacon(Location location, int radius){
//        int chunkX = location.blockX() >> 4;
//        int chunkY = location.blockY() >> 4;
//
//        for (int x = chunkX - radius; x <= chunkX + radius; x++){
//            for (int y = chunkY -radius; x <= chunkY + radius; y++){
//                location.getWorld().getChunkAt(x, y).load();
//            }
//        }
//    }
//}
