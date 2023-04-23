package com.serialplotter.server.stream;

import com.serialplotter.server.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class StreamService {
    private final StreamRepository streamRepository;

    @Autowired
    public StreamService(StreamRepository streamRepository) {
        this.streamRepository = streamRepository;
    }

    public List<Stream> getStreams() {
        return streamRepository.findAll();
    }

    public Stream getStream(Long streamId) {
        Optional<Stream> streamOptional = streamRepository.findById(streamId);

        if(streamOptional.isPresent()) {
            Stream stream = streamOptional.get();
            return stream;
        } else {
            throw new IllegalArgumentException("Stream ID[" + streamId + "] does not exist");
        }
    }

    public Stream insertStream(Stream stream) {
        if (stream.getStreamName().length() < 1) {
            throw new IllegalArgumentException("Stream name must be at least 1 character long");
        }
        return streamRepository.save(stream);
    }

    public Stream updateStream(Long streamId, Stream updatedStream) {
        Optional<Stream> optionalStream = streamRepository.findById(updatedStream.getStreamId());

        if (optionalStream.isPresent()) {
            Stream stream = optionalStream.get();

            /*
            TODO:
            - validation on baud, port
             */
            if (updatedStream.getStreamName().length() < 1) {
                throw new IllegalArgumentException("stream name cannot be empty");
            } else {
                stream.setStreamName(updatedStream.getStreamName());
            }

            if(updatedStream.getBaudRate() == null) {
                throw new IllegalArgumentException("baud rate cannot be empty");
            } else {
                stream.setBaudRate(updatedStream.getBaudRate());
            }

            if(updatedStream.getPort().length()<1) {
                throw new IllegalArgumentException("port cannot be empty");
            } else {
                stream.setPort(updatedStream.getPort());
            }

            streamRepository.save(stream);
        }
        return updatedStream;
    }

    public void softDeleteStream(Long streamId) {
        Optional<Stream> optionalStream = streamRepository.findById(streamId);

        if(optionalStream.isPresent()) {
            Stream stream = optionalStream.get();
            stream.setDeleted(true);
            streamRepository.save(stream);
        } else {
            throw new IllegalArgumentException("stream ID [" + streamId + "] not found");
        }
    }

    public void hardDeleteStream(Long streamId) {
        Optional<Stream> optionalStream = streamRepository.findById(streamId);

        if(optionalStream.isPresent()) {
            Stream stream = optionalStream.get();
            streamRepository.deleteById(streamId);
        } else {
            throw new IllegalArgumentException("stream ID [" + streamId + "] not found");
        }
    }

}
