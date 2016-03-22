package com.citi.spellingBee.domain;

public class WordContent {
	Long wordId;
	String word;
	String wordLevel;
	String status;
	String rejectedReason;
	String userId;
	String pronunciationAudio;
	
	public Long getWordId() {
		return wordId;
	}
	public void setWordId(Long wordId) {
		this.wordId = wordId;
	}
	
	public String getWordLevel() {
		return wordLevel;
	}
	public void setWordLevel(String wordLevel) {
		this.wordLevel = wordLevel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRejectedReason() {
		return rejectedReason;
	}
	public void setRejectedReason(String rejectedReason) {
		this.rejectedReason = rejectedReason;
	}

	@Override
	public String toString() {
		return "WordContent [wordId=" + wordId + ", word=" + word
				+ ", wordLevel=" + wordLevel + ", status=" + status
				+ ", rejectedReason=" + rejectedReason + ", userId=" + userId
				+ "]";
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getPronunciationAudio() {
		return pronunciationAudio;
	}
	public void setPronunciationAudio(String pronunciationAudio) {
		this.pronunciationAudio = pronunciationAudio;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
