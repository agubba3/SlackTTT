package com.ttt.Controller;

/**
 * Created by agubba on 10/18/16.
 */

import com.ttt.Exceptions.GameCreationException;
import com.ttt.Service.TTTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/imageOut")
public class ImageController {

    @Autowired
    private TTTService tttService; //dependency inject this service into the controller to reduce coupling in our system

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity outImage(@RequestParam(value="channel_id", defaultValue="") String channel_id
            , @RequestParam(value="state", defaultValue="") String state
            , @RequestParam(value="count", defaultValue="") String count) {

        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();

        try {
            //last move
            if (!state.equals("")) {
                BufferedImage lastboard = tttService.getImgByState(state);
                ImageIO.write(lastboard, "jpeg", jpegOutputStream);
            } else {
                BufferedImage board = tttService.getImgByChannel(channel_id);
                ImageIO.write(board, "jpeg", jpegOutputStream);
            }
        } catch (GameCreationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        byte[] imgByte = jpegOutputStream.toByteArray();

        return ResponseEntity.ok(imgByte);

    }

}
