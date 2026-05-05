package dailymotion.miner.service;

import dailymotion.miner.model.Channel;
import dailymotion.miner.model.Comment;
import dailymotion.miner.model.User;
import dailymotion.miner.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class DailymotionService {

    private static final String DAILYMOTION_API = "https://api.dailymotion.com";
    private static final String VIDEOMINER_API = "http://localhost:8080";

    @Autowired
    private RestTemplate restTemplate;

    public Channel getChannel(String id, int maxVideos, int maxPages) {

        String channelUrl = DAILYMOTION_API + "/channel/" + id + "?fields=id,name,description,created_time";

        Channel channel = restTemplate.getForObject(channelUrl, Channel.class);


        if (channel == null) {
            throw new RuntimeException("Canal no encontrado: " + id);
        }

        String videosUrl = DAILYMOTION_API + "/channel/" + id + "/videos?fields=id,title,description,created_time,owner,tags&limit=" + maxVideos;

        VideoList videoList = restTemplate.getForObject(videosUrl, VideoList.class);

        //Bucle para deserializar los videos
        List<Video> videos = new ArrayList<>();
        if (videoList != null && videoList.getList() != null) {
            for (Video video : videoList.getList()) {



                String userUrl = DAILYMOTION_API + "/user/" + video.getUserId() + "?fields=id,screenname,url,avatar_120_url";

                User user = restTemplate.getForObject(userUrl, User.class);

                video.setUser(user);

                String captionsUrl = DAILYMOTION_API + "/video/" + video.getId() + "/subtitles?fields=id,url,language";

                 CaptionList captionList = restTemplate.getForObject(captionsUrl, CaptionList.class);

                if (captionList != null && captionList.getList() != null) {
                    video.setCaptions(captionList.getList());
                } else {
                    video.setCaptions(new ArrayList<>());
                }

                //Bucle para generar los comments a través de la lista de string proporcionada en
                // la clase Video
                List<Comment> commentList = new ArrayList<>();
                if (video.getComments() != null) {
                    for (int i = 0; i < video.getComments().size(); i++) {
                        Comment comment = new Comment();
                        comment.setId(video.getId() + "_tag_" + i);
                        comment.setText(video.getComments().get(i));
                        comment.setCreatedOn(video.getReleaseTime());
                        commentList.add(comment);
                    }
                }
                video.setCommentList(commentList);


                videos.add(video);
            }
        }

        channel.setVideos(videos);
        return channel;
    }

    public Channel getAndSendChannel(String id, int maxVideos, int maxPages) {
        Channel channel = getChannel(id, maxVideos, maxPages);

        try {
            String videoMinerUrl = VIDEOMINER_API + "/videominer/channels";
            restTemplate.postForEntity(videoMinerUrl, channel, Channel.class);
        } catch (Exception e) {
            System.out.println("Error al enviar a VideoMiner: " + e.getMessage());
            throw e;
        }

        return channel;
    }
}