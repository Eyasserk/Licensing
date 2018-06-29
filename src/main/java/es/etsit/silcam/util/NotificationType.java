package es.etsit.silcam.util;

import lombok.Getter;

@Getter
public enum NotificationType {
	STATE_UPDATED(1), PHASE_UPDATED(2);
	
	private int value;
	
	private NotificationType(int value) {
		this.value = value;
	}
	
	public static NotificationType fromValue(int value) {
		switch (value) {
			case 1: return NotificationType.STATE_UPDATED;
			case 2: return NotificationType.PHASE_UPDATED;
			default: return null;
		}
	}
}
