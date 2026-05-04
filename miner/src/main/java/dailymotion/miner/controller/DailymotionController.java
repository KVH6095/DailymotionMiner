package dailymotion.miner.controller;

import dailymotion.miner.model.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import dailymotion.miner.service.DailymotionService;

@RestController
@RequestMapping("/dailymotionminer")
public class DailymotionController {

    @Autowired
    private DailymotionService dailymotionService;

    // Operación POST: obtiene los datos de Dailymotion y los envía a VideoMiner
    @PostMapping("/{id}")
    public ResponseEntity<Channel> mineChannel(
            @PathVariable String id,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxPages) {

        try {
            Channel channel = dailymotionService.getAndSendChannel(id, maxVideos, maxPages);
            return ResponseEntity.status(201).body(channel); // 201 Created
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.notFound().build(); // 404
        } catch (HttpClientErrorException.BadRequest e) {
            return ResponseEntity.badRequest().build(); // 400
        } catch (HttpServerErrorException e) {
            return ResponseEntity.internalServerError().build(); // 500
        }
    }

    // Operación GET: solo lectura para pruebas, no envía a VideoMiner
    @GetMapping("/{id}")
    public ResponseEntity<Channel> previewChannel(
            @PathVariable String id,
            @RequestParam(defaultValue = "10") int maxVideos,
            @RequestParam(defaultValue = "2") int maxPages) {

        try {
            Channel channel = dailymotionService.getChannel(id, maxVideos, maxPages);
            return ResponseEntity.ok(channel); // 200
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.notFound().build(); // 404
        } catch (HttpClientErrorException.BadRequest e) {
            return ResponseEntity.badRequest().build(); // 400
        } catch (HttpServerErrorException e) {
            return ResponseEntity.internalServerError().build(); // 500
        }
    }
}