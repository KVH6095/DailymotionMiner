package dailymotion.miner.controller;

import dailymotion.miner.model.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import dailymotion.miner.service.DailymotionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/dailymotionminer")
@Tag(name = "Dailymotion Miner", description = "Operaciones para extraer datos de canales de Dailymotion")
public class DailymotionController {

    @Autowired
    private DailymotionService dailymotionService;

    // Operación POST: obtiene los datos de Dailymotion y los envía a VideoMiner
    @Operation(
        summary = "Obtener y enviar canal", 
        description = "Obtiene los datos de un canal de Dailymotion y los envía automáticamente a VideoMiner"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Canal minado y enviado con éxito a VideoMiner",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Channel.class)) }),
            @ApiResponse(responseCode = "400", description = "Petición inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Canal no encontrado en Dailymotion", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PostMapping("/{id}")
    public ResponseEntity<Channel> mineChannel(
            @Parameter(description = "ID del canal de Dailymotion a minar") @PathVariable String id,
            @Parameter(description = "Número máximo de vídeos a obtener por canal") @RequestParam(defaultValue = "10") int maxVideos,
            @Parameter(description = "Número máximo de páginas (comentarios/captions)") @RequestParam(defaultValue = "2") int maxPages) {

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
    @Operation(
        summary = "Previsualizar canal", 
        description = "Obtiene los datos de un canal de Dailymotion solo para previsualización (no los envía a VideoMiner)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Canal recuperado con éxito",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Channel.class)) }),
            @ApiResponse(responseCode = "400", description = "Petición inválida", content = @Content),
            @ApiResponse(responseCode = "404", description = "Canal no encontrado en Dailymotion", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Channel> previewChannel(
            @Parameter(description = "ID del canal de Dailymotion a previsualizar") @PathVariable String id,
            @Parameter(description = "Número máximo de vídeos a obtener por canal") @RequestParam(defaultValue = "10") int maxVideos,
            @Parameter(description = "Número máximo de páginas (comentarios/captions)") @RequestParam(defaultValue = "2") int maxPages) {

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