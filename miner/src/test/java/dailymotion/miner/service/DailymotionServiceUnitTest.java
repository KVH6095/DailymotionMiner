package dailymotion.miner.service;

import dailymotion.miner.model.Channel;
import dailymotion.miner.model.Comment;
import dailymotion.miner.model.User;
import dailymotion.miner.model.Video;
import dailymotion.miner.service.CaptionList;
import dailymotion.miner.service.DailymotionService;
import dailymotion.miner.service.VideoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DailymotionServiceUnitTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private DailymotionService dailymotionService;

    private Channel fakeChannel;
    private Video fakeVideo;
    private User fakeUser;
    private VideoList fakeVideoList;
    private CaptionList fakeCaptionList;

    @BeforeEach
    void setUp() {
        fakeChannel = new Channel();
        fakeChannel.setId("chan1");
        fakeChannel.setName("Test Channel");

        fakeUser = new User();
        fakeUser.setId("1");
        fakeUser.setName("Test User");

        fakeVideo = new Video();
        fakeVideo.setId("vid1");
        fakeVideo.setName("Test Video");
        fakeVideo.setUserId("user1");
        fakeVideo.setComments(List.of("tag1", "tag2"));
        fakeVideo.setReleaseTime("2024-01-01");

        fakeVideoList = new VideoList();
        fakeVideoList.setList(List.of(fakeVideo));

        fakeCaptionList = new CaptionList();
        fakeCaptionList.setList(List.of());
    }

    @Test
    void whenGetChannel_thenReturnChannelWithVideos() {
        when(restTemplate.getForObject(contains("/channel/chan1?fields"), eq(Channel.class)))
                .thenReturn(fakeChannel);
        when(restTemplate.getForObject(contains("/channel/chan1/videos"), eq(VideoList.class)))
                .thenReturn(fakeVideoList);
        when(restTemplate.getForObject(contains("/user/user1"), eq(User.class)))
                .thenReturn(fakeUser);
        when(restTemplate.getForObject(contains("/subtitles"), eq(CaptionList.class)))
                .thenReturn(fakeCaptionList);

        Channel result = dailymotionService.getChannel("chan1", 10, 2);

        assertNotNull(result);
        assertEquals("chan1", result.getId());
        assertEquals(1, result.getVideos().size());
        assertEquals("vid1", result.getVideos().get(0).getId());
    }

    @Test
    void whenGetChannel_thenVideoHasUserAssigned() {
        when(restTemplate.getForObject(contains("/channel/chan1?fields"), eq(Channel.class)))
                .thenReturn(fakeChannel);
        when(restTemplate.getForObject(contains("/channel/chan1/videos"), eq(VideoList.class)))
                .thenReturn(fakeVideoList);
        when(restTemplate.getForObject(contains("/user/user1"), eq(User.class)))
                .thenReturn(fakeUser);
        when(restTemplate.getForObject(contains("/subtitles"), eq(CaptionList.class)))
                .thenReturn(fakeCaptionList);

        Channel result = dailymotionService.getChannel("chan1", 10, 2);

        User author = result.getVideos().get(0).getUser();
        assertNotNull(author);
        assertEquals("1", author.getId());
    }

    @Test
    void whenGetChannel_thenTagsConvertedToComments() {
        when(restTemplate.getForObject(contains("/channel/chan1?fields"), eq(Channel.class)))
                .thenReturn(fakeChannel);
        when(restTemplate.getForObject(contains("/channel/chan1/videos"), eq(VideoList.class)))
                .thenReturn(fakeVideoList);
        when(restTemplate.getForObject(contains("/user/user1"), eq(User.class)))
                .thenReturn(fakeUser);
        when(restTemplate.getForObject(contains("/subtitles"), eq(CaptionList.class)))
                .thenReturn(fakeCaptionList);

        Channel result = dailymotionService.getChannel("chan1", 10, 2);

        List<Comment> comments = result.getVideos().get(0).getCommentList();
        assertNotNull(comments);
        assertEquals(2, comments.size());
        assertNotNull(comments.get(0).getId());
        assertEquals("tag1", comments.get(0).getText());
        assertNotNull(comments.get(1).getId());
        assertEquals("tag2", comments.get(1).getText());
    }

    @Test
    void whenGetChannel_thenCommentInheritsReleaseTime() {
        when(restTemplate.getForObject(contains("/channel/chan1?fields"), eq(Channel.class)))
                .thenReturn(fakeChannel);
        when(restTemplate.getForObject(contains("/channel/chan1/videos"), eq(VideoList.class)))
                .thenReturn(fakeVideoList);
        when(restTemplate.getForObject(contains("/user/user1"), eq(User.class)))
                .thenReturn(fakeUser);
        when(restTemplate.getForObject(contains("/subtitles"), eq(CaptionList.class)))
                .thenReturn(fakeCaptionList);

        Channel result = dailymotionService.getChannel("chan1", 10, 2);

        Comment comment = result.getVideos().get(0).getCommentList().get(0);
        assertEquals("2024-01-01", comment.getCreatedOn());
    }

    @Test
    void whenGetChannelWithNullVideoList_thenReturnChannelWithEmptyVideos() {
        when(restTemplate.getForObject(contains("/channel/chan1?fields"), eq(Channel.class)))
                .thenReturn(fakeChannel);
        when(restTemplate.getForObject(contains("/channel/chan1/videos"), eq(VideoList.class)))
                .thenReturn(null);

        Channel result = dailymotionService.getChannel("chan1", 10, 2);

        assertNotNull(result);
        assertTrue(result.getVideos().isEmpty());
    }

    @Test
    void whenGetChannelNotFound_thenThrowException() {
        when(restTemplate.getForObject(contains("/channel/chan1?fields"), eq(Channel.class)))
                .thenReturn(null);

        assertThrows(RuntimeException.class,
                () -> dailymotionService.getChannel("chan1", 10, 2));
    }

    @Test
    void whenGetChannelWithNullCaptions_thenVideoHasEmptyCaptions() {
        when(restTemplate.getForObject(contains("/channel/chan1?fields"), eq(Channel.class)))
                .thenReturn(fakeChannel);
        when(restTemplate.getForObject(contains("/channel/chan1/videos"), eq(VideoList.class)))
                .thenReturn(fakeVideoList);
        when(restTemplate.getForObject(contains("/user/user1"), eq(User.class)))
                .thenReturn(fakeUser);
        when(restTemplate.getForObject(contains("/subtitles"), eq(CaptionList.class)))
                .thenReturn(null);

        Channel result = dailymotionService.getChannel("chan1", 10, 2);

        assertNotNull(result.getVideos().get(0).getCaptions());
        assertTrue(result.getVideos().get(0).getCaptions().isEmpty());
    }

}
