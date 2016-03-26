package net.adventuria.particle;

public enum ParticleType {
	HEART_FULL("particle_heart_full"),
	HEART_CONTAINER("particle_heart_outline"),
	HEART_HALF("particle_heart_half"),
	HEART_WITHER_FULL("particle_heart_black_full"),
	HEART_WITHER_HALF("particle_heart_black_half"),
	HEART_POISON_FULL("particle_heart_green_full"),
	HEART_POISON_HALF("particle_heart_green_half"),
	HEART_GOLDEN_FULL("particle_heart_gold_full"),
	HEART_GOLDEN_HALF("particle_heart_gold_half"),
	HEART_CRYSTAL_FULL("particle_heart_blue_full"),
	HEART_CRYSTAL_HALF("particle_heart_blue_half"),
	HUNGER_FULL("particle_meat_full"),
	HUNGER_HALF("particle_meat_half"),
	HUNGER_POISONED_FULL("particle_meat_poisoned_full"),
	HUNGER_POISONED_HALF("particle_meat_poisoned_half"),
	HUNGER_GOLDEN_FULL("particle_meat_gold_full"),
	HUNGER_GOLDEN_HALF("particle_meat_gold_half"),
	AIRBUBBLE_FULL("particle_bubble_full"),
	AIRBUBBLE_HALF("particle_bubble_half"),
	MANA_FULL("particle_mana_full"),
	MANA_34("particle_mana_3_4"),
	MANA_HALF("particle_mana_half"),
	MANA_14("particle_mana_1_4");
	
	private String textureAlias;
	
	private ParticleType(String textureAlias) {
		this.textureAlias = textureAlias;
	}
	
	public String getTextureAlias() {
		return this.textureAlias;
	}
}
