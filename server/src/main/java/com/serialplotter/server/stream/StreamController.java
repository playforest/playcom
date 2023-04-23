package com.serialplotter.server.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/streams")
public class StreamController {
    private final StreamService streamService;

    @Autowired
    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @GetMapping(path="/")
    public List<Stream> getStreams() {
        return streamService.getStreams();
    }

    @GetMapping(path="/{streamId}")
    public Stream getStream(@PathVariable Long streamId) {
        return streamService.getStream(streamId);
    }

    @PostMapping()
    public Stream createStream(@RequestBody Stream stream) {
        streamService.insertStream(stream);
        return stream;
    }

    @PutMapping(path="/{streamId}")
    public Stream updateStream(@PathVariable Long streamId, @RequestBody Stream stream) {
        streamService.updateStream(streamId, stream);
        return stream;
    }


}
