package dailymotion.miner.service;

import dailymotion.miner.model.Channel;
import dailymotion.miner.model.Comment;
import dailymotion.miner.model.Video;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DailymotionServiceTest {

    @Autowired
    private DailymotionService service;

    @Test
    @DisplayName("Extraer datos del canal 'news' y verificar conversión de tags a comentarios")
    void testGetChannel() {
        // Obtenemos un canal real de Dailymotion (limitando a 3 vídeos para que sea rápido)
        Channel channel = service.getChannel("news", 3, 1);

        // 1. Verificamos que el canal se ha construido correctamente
        assertNotNull(channel, "El canal no debería ser nulo");
        assertEquals("news", channel.getId(), "El ID del canal debe coincidir");
        assertNotNull(channel.getName(), "El canal debe tener un nombre");
        assertFalse(channel.getVideos().isEmpty(), "El canal debería contener vídeos");

        // 2. Verificamos la información de un vídeo
        Video video = channel.getVideos().get(0);
        assertNotNull(video.getId(), "El vídeo debe tener ID");
        assertNotNull(video.getName(), "El vídeo debe tener título (name)");
        
        // 3. Verificamos que se asocia el usuario creador al vídeo
        assertNotNull(video.getUser(), "El vídeo debe tener asignado su creador (User)");
        assertNotNull(video.getUser().getId(), "El creador debe tener un ID");

        // 4. Verificamos la lógica especial: Los tags se han convertido a Comments
        assertNotNull(video.getCommentList(), "La lista de comentarios no debe ser nula");
        
        // Comprobamos el formato de los comentarios si el vídeo original tenía tags
        if (!video.getCommentList().isEmpty()) {
            Comment comment = video.getCommentList().get(0);
            assertNotNull(comment.getId(), "El comentario debe tener ID");
            assertTrue(comment.getId().contains(video.getId() + "_tag_"), 
                    "El ID del comentario debe estar generado a partir del ID del vídeo y el prefijo '_tag_'");
            assertNotNull(comment.getText(), "El texto del comentario no puede estar vacío (corresponde al tag)");
        }
    }

    @Test
    @DisplayName("Comprobar error 404 al buscar un canal que no existe")
    void testGetChannelNotFound() {
        // Verificamos que lance la excepción correcta si buscamos un canal inventado
        assertThrows(HttpClientErrorException.NotFound.class, () -> {
            service.getChannel("este-canal-no-existe-123456789", 1, 1);
        }, "Debería lanzar un error 404 NotFound si el canal no existe en Dailymotion");
    }
}
