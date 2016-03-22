package com.citi.spellingBee.domain;

public class WordPronunciation {
	Long pronunciationId;
	Long wordId;
	String pronunciationAudio;
	String userId;
	
	public Long getPronunciationId() {
		return pronunciationId;
	}
	public void setPronunciationId(Long pronunciationId) {
		this.pronunciationId = pronunciationId;
	}
	public Long getWordId() {
		return wordId;
	}
	public void setWordId(Long wordId) {
		this.wordId = wordId;
	}
	public String getPronunciationAudio() {
		return pronunciationAudio;
	}
	public void setPronunciationAudio(String pronunciationAudio) {
		this.pronunciationAudio = pronunciationAudio;
	}

	
	@Override
	public String toString() {
		return "WordPronunciation [pronunciationId=" + pronunciationId
				+ ", wordId=" + wordId + ", pronunciationAudio="
				+ pronunciationAudio + ", userId=" + userId + "]";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
