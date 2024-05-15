package com.ssafy.tipsy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;



@RequiredArgsConstructor
@Tag(name = "GameMessage", description = "게임 소켓 메시지 핸들러")
public class GameMessageHandler {

    @MessageMapping("/game/room/{rid}")
    @Operation(summary = "방 채팅", description = "방 내부 인원 채팅 내용 전달")
    public void communicationInGameRoom(@DestinationVariable String rid, GameCommDto gameCommDto) {

    }

    @MessageMapping("/game/room/{rid}")
    @Operation(summary = "레디", description = "레디 변경 전달")
    public void onReady(@DestinationVariable String rid, GameCommDto gameCommDto) {
        //레디 여부 전달
    }

    @MessageMapping("/game/room/{rid}")
    @Operation(summary = "게임 스타트", description = "방장이 레디할 때, 게임 스타트")
    public void onGameStart(@DestinationVariable String rid, GameCommDto gameCommDto) {
        //어떤 게임 진행할 건지 전달 필요
    }

    @MessageMapping("/game/play/liar-game/{rid}")
    public void playLiarGame(@DestinationVariable String rid, LiarRequestDto liarRequestDto) {
        String type = liarRequestDto.getType();
        if (type.equals("Enter")) {
            if (gameServiceImpl.countUser(rid)) {
                LiarResponseDto liarResponseDto = gameServiceImpl.getLiarData(rid);
                simpMessagingTemplate.convertAndSend("/sub/play/liar-game/" + rid, liarResponseDto);
            }
        } else {
            String nickname = liarRequestDto.getNickname();
            String result = gameServiceImpl.voteLiar(rid, nickname);
            if (result != null) {
                simpMessagingTemplate.convertAndSend("/sub/play/liar-game/" + rid, result);
                simpMessagingTemplate.convertAndSend("/topic/play/liar-game/" + rid, result);
            }
        }
    }

    @MessageMapping("/game/play/croco-game/{rid}")
    public void playCrocoGame(@DestinationVariable String rid, CrocoDto crocoDto) {
        if (crocoDto.getType().equals("Start")) {
            if (gameServiceImpl.countUser(rid)) {
                gameServiceImpl.getCrocoTeeth(rid);
                String next = gameServiceImpl.findNextUser(rid, "");
                simpMessagingTemplate.convertAndSend("/sub/play/croco-game/" + rid, new CrocoDto("Start", next, 0));
            }
        } else if (crocoDto.getType().equals("Play")) {
            Boolean check = gameServiceImpl.checkCrocoIdx(rid, crocoDto.getIdx());
            if (!check) {
                String next = gameServiceImpl.findNextUser(rid, crocoDto.getNickname());
                simpMessagingTemplate.convertAndSend("/sub/play/croco-game/" + rid,
                        new CrocoDto("Turn", next, crocoDto.getIdx()));
            }else {
                CrocoDto data = new CrocoDto("Result", crocoDto.getNickname(),0);
                gameServiceImpl.setHost(rid, crocoDto.getNickname());
                simpMessagingTemplate.convertAndSend("/sub/play/croco-game/"+rid, data);
                simpMessagingTemplate.convertAndSend("/topic/play/croco-game/"+rid, data);
            }
        }
    }

    @MessageMapping("/game/play/drink-game/{rid}")
    public void playDrinkGame(@DestinationVariable String rid, CommonGameDto commonGameDto) {
        gameServiceImpl.putRecord(rid, commonGameDto);
        if(gameServiceImpl.countUser(rid)) {
            List<CommonGameDto> list = gameServiceImpl.sortRecord(rid);
            simpMessagingTemplate.convertAndSend("/sub/play/drink-game/"+rid, list);
            simpMessagingTemplate.convertAndSend("/topic/play/drink-game/"+rid, list);
        }
    }

    @MessageMapping("/game/play/drag-game/{rid}")
    public void playDragGame(@DestinationVariable String rid, CommonGameDto commonGameDto) {
        gameServiceImpl.putRecord(rid, commonGameDto);
        if(gameServiceImpl.countUser(rid)) {
            List<CommonGameDto> list = gameServiceImpl.sortRecord(rid);
            simpMessagingTemplate.convertAndSend("/sub/play/drag-game/"+rid, list);
            simpMessagingTemplate.convertAndSend("/topic/play/drag-game/"+rid, list);
        }
    }

    @MessageMapping("/game/play/roulette-game/{rid}")
    public void playRouletteGame(@DestinationVariable String rid, String type) {
        if(type.equals("Enter")) {
            if(gameServiceImpl.countUser(rid)) {
                simpMessagingTemplate.convertAndSend("/sub/play/roulette-game/"+rid,
                        gameServiceImpl.getRouletteResponseDto(rid));
            }
        } else {
            if(gameServiceImpl.countUser(rid)) {
                simpMessagingTemplate.convertAndSend("/topic/play/roulette-game/" + rid, type);
            }
        }
    }

    @MessageMapping("/game/play/ordering-game/{rid}")
    public void playOrderingGame(@DestinationVariable String rid, CommonGameDto commonGameDto) {
        gameServiceImpl.putRecord(rid, commonGameDto);
        if(gameServiceImpl.countUser(rid)) {
            List<CommonGameDto> list = gameServiceImpl.sortRecord(rid);
            simpMessagingTemplate.convertAndSend("/sub/play/ordering-game/"+rid, list);
            simpMessagingTemplate.convertAndSend("/topic/play/ordering-game/"+rid, list);
        }
    }

    @MessageMapping("/game/force-exit/{rid}")
    public void ForceExit(@DestinationVariable String rid, ForceExitDto dto) {
        List<GameUserDto> data = gameServiceImpl.communicateInGameRoom(rid, new GameCommDto("Exit", dto.getGameUserDto()));
        if(data != null) {
            gameServiceImpl.onGameStart(rid);
            simpMessagingTemplate.convertAndSend("/sub/room/" + rid, data);
            switch(dto.getGid()) {
                case -1:
                    simpMessagingTemplate.convertAndSend("/sub/select/" + rid, "ForceExit," + dto.getGameUserDto().getNickname());
                case 1:
                    simpMessagingTemplate.convertAndSend("/sub/play/liar-game/" + rid, "ForceExit," + dto.getGameUserDto().getNickname());
                case 2:
                    gameServiceImpl.forceExit(rid);
                    simpMessagingTemplate.convertAndSend("/sub/play/croco-game/" + rid, "ForceExit," + dto.getGameUserDto().getNickname());
                case 3:
                    gameServiceImpl.forceExit(rid);
                    simpMessagingTemplate.convertAndSend("/sub/play/drink-game/" + rid, "ForceExit," + dto.getGameUserDto().getNickname());
                case 4:
                    gameServiceImpl.forceExit(rid);
                    simpMessagingTemplate.convertAndSend("/sub/play/drag-game/" + rid, "ForceExit," + dto.getGameUserDto().getNickname());
                case 5:
                    simpMessagingTemplate.convertAndSend("/sub/play/roulette-game/" + rid, "ForceExit," + dto.getGameUserDto().getNickname());
                case 6:
                    gameServiceImpl.forceExit(rid);
                    simpMessagingTemplate.convertAndSend("/sub/play/ordering-game/" + rid, "ForceExit," + dto.getGameUserDto().getNickname());
            }
        }
    }
}
