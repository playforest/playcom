package com.serialplotter.server;

import com.serialplotter.server.sync.S3SyncService;
import com.serialplotter.server.user.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
		S3SyncService s3service = new S3SyncService();
		s3service.listObjects("serialstreams");
//		s3service.downloadObject("serialstreams", "1/1");

		s3service.writeObject("2/file","123456789");
	}


}
