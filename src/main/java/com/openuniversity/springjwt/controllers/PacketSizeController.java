package com.openuniversity.springjwt.controllers;

import com.openuniversity.springjwt.models.PacketSizes;
import com.openuniversity.springjwt.payload.request.PacketSizeAddRequest;
import com.openuniversity.springjwt.payload.request.PacketSizeEditRequest;
import com.openuniversity.springjwt.payload.response.MessageResponse;
import com.openuniversity.springjwt.repository.PacketSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Controller
@RequestMapping("/api/packetSize")
public class PacketSizeController {

    @Autowired
    PacketSizeRepository packetSizeRepository;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllPacketSizeDetails() {
        boolean packetSizeExists = false;
        List <Long> id = packetSizeRepository.getAllIds();
        long count = Collections.max(id);
        List<PacketSizes> list= new ArrayList<PacketSizes>();
        if (count > 0){
            packetSizeExists = true;
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: No packet sizes available!"));
        }
        if(packetSizeExists){

            for(long i = 1; i<= count; i++) {
                list.add(packetSizeRepository.getAllById(i));
            }
        }
        else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: No packet sizes available!"));
        }

        return new ResponseEntity<List<PacketSizes>>(list, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addPacketSize(@RequestBody PacketSizeAddRequest packetSizeAddRequest){

        if (packetSizeRepository.existsByPacketWeight(packetSizeAddRequest.getPacketWeight())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Packet size is already taken!"));
        }
        else {
            PacketSizes packetSizes = new PacketSizes(packetSizeAddRequest.getSizeName(),
                    packetSizeAddRequest.getPacketWeight());

            packetSizeRepository.save(packetSizes);
            return ResponseEntity.ok(new MessageResponse("Packet size added successfully!"));
        }
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity <?> editPacketSizes(@RequestBody PacketSizeEditRequest packetSizeEditRequest, @PathVariable("id") long id){
        String newSizeName = packetSizeEditRequest.getSizeName();
        String newPacketWeight = packetSizeEditRequest.getPacketWeight();

        if (packetSizeRepository.existsByPacketWeight(packetSizeEditRequest.getPacketWeight())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Packet size is already taken!"));
        }
        else {
            PacketSizes packetSizes = packetSizeRepository.findById(id);
            packetSizes.setSizeName(newSizeName);
            packetSizes.setPacketWeight(newPacketWeight);

            packetSizeRepository.save(packetSizes);

            return ResponseEntity.ok(new MessageResponse("Packet size edited successfully!"));
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity <?> deletePacketSizes(@PathVariable("id") long id){
        PacketSizes packetSizes = packetSizeRepository.findById(id);
        packetSizeRepository.delete(packetSizes);
        return ResponseEntity.ok(new MessageResponse("Packet size deleted successfully!"));
    }

}
