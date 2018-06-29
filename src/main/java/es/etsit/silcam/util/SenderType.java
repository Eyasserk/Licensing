package es.etsit.silcam.util;

import lombok.Getter;

@Getter
public enum SenderType {
	SILCAM(1), Solicitante(2);
	
	int value;
	
	private SenderType(int value) {
		this.value = value;
	}
	
	public static SenderType fromValue(int value) {
		switch (value) {
		case 1: return SenderType.SILCAM;
		case 2: return SenderType.Solicitante;	
		default: return null;
		}
	}
}
