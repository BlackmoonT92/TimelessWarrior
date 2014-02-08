package com.blackmoon.runtime.GameLevels;

import com.blackmoon.object.Enemy;

/**
 * This class holds an array of all of the levels that can be played in the game
 * as well as helper methods to retrieve specific levels.
 * 
 *** @author Brian Broyles - IFL Game Studio
 **/
public class Levels {

	// ====================================================
	// CONSTANTS
	// ====================================================
	private static final LevelDef[] AvailableLevels = new LevelDef[] {
			new LevelDef(1, 1, new float[] { 10f, 100f, 0f, 0.9f, 10f, 3100f,
					3190f, 0f, 1f, 10f }, 1, new DeathsInLevelDef[] {
					new DeathsInLevelDef(1700f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(2700f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(6000f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(12000f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(9500f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(7700f, 420f, 500f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(1500f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(18400f, 1100f, 300f, 900f,
							DeathsInLevelDef.BeamType.MetalStatic),

			}, new BeamsInLevelDef[] {
					new BeamsInLevelDef(1000f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(1600f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(2200f, 932f, 450f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(2750f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(3400f, 900f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(3900f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(4700f, 550f, 400f, 15f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(5400f, 900f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(6200f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(6700f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(7300f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(7700f, 400f, 500f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(7800f, 1300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(8500f, 1500f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(9200f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(9400f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(10000f, 350f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(10700f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(11300f, 950f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(12000f, 650f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(12600f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(13100f, 400f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(13900f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(14200f, 850f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(14900f, 1200f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(15500f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(16100f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(16800f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(17500f, 950f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18000f, 1100f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18100f, 500f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18500f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(19300f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(19300f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

			},

			new Coins[] {

			new Coins(2800f, 1000f, Coins.Coin.Normal),
					new Coins(9000f, 1700f, Coins.Coin.Normal),
					new Coins(7800f, 600f, Coins.Coin.Normal),
					new Coins(10000f, 464f, Coins.Coin.Normal),
					new Coins(16000f, 600f, Coins.Coin.Normal),
					new Coins(15300f, 1500, Coins.Coin.Normal),
					new Coins(19500f, 400f, Coins.Coin.Normal) },

					new EnemyObject[] {

							new EnemyObject(3500f, 1000f,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(9900f, 100f,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(16200f, 1000,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(6800f, 700f,
									EnemyObject.EnemyStatus.Normal) }),

			new LevelDef(2, 1, new float[] { 10f, 100f, 0f, 0.9f, 10f, 3100f,
					3190f, 0f, 1f, 10f }, 1, new DeathsInLevelDef[] {
					new DeathsInLevelDef(1700f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(2700f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(6000f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(12000f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(9500f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(7700f, 420f, 500f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(1500f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(18400f, 1100f, 300f, 900f,
							DeathsInLevelDef.BeamType.MetalStatic),

			}, new BeamsInLevelDef[] {
					new BeamsInLevelDef(1000f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(1600f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(2200f, 932f, 450f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(2750f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(3400f, 900f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(3900f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(4700f, 550f, 400f, 15f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(5400f, 900f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(6200f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(6700f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(7300f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(7700f, 400f, 500f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(7800f, 1300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(8500f, 1500f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(9200f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(9400f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(10000f, 350f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(10700f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(11300f, 950f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(12000f, 650f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(12600f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(13100f, 400f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(13900f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(14200f, 850f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(14900f, 1200f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(15500f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(16100f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(16800f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(17500f, 950f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18000f, 1100f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18100f, 500f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18500f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(19300f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(19300f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

			},

			new Coins[] {

			new Coins(2800f, 1000f, Coins.Coin.Normal),
					new Coins(9000f, 1700f, Coins.Coin.Normal),
					new Coins(7800f, 600f, Coins.Coin.Normal),
					new Coins(10000f, 464f, Coins.Coin.Normal),
					new Coins(16000f, 600f, Coins.Coin.Normal),
					new Coins(15300f, 1500, Coins.Coin.Normal),
					new Coins(19500f, 400f, Coins.Coin.Normal) },

					new EnemyObject[] {

							new EnemyObject(3500f, 1000f,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(9900f, 100f,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(16200f, 1000,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(6800f, 700f,
									EnemyObject.EnemyStatus.Normal) }),

			new LevelDef(3, 1, new float[] { 10f, 100f, 0f, 0.9f, 10f, 3100f,
					3190f, 0f, 1f, 10f }, 1, new DeathsInLevelDef[] {
					new DeathsInLevelDef(1700f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(2700f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(6000f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(12000f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(9500f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(7700f, 420f, 500f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(1500f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(18400f, 1100f, 300f, 900f,
							DeathsInLevelDef.BeamType.MetalStatic),

			}, new BeamsInLevelDef[] {
					new BeamsInLevelDef(1000f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(1600f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(2200f, 932f, 450f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(2750f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(3400f, 900f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(3900f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(4700f, 550f, 400f, 15f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(5400f, 900f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(6200f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(6700f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(7300f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(7700f, 400f, 500f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(7800f, 1300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(8500f, 1500f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(9200f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(9400f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(10000f, 350f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(10700f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(11300f, 950f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(12000f, 650f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(12600f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(13100f, 400f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(13900f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(14200f, 850f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(14900f, 1200f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(15500f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(16100f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(16800f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(17500f, 950f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18000f, 1100f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18100f, 500f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18500f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(19300f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(19300f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

			},

			new Coins[] {

			new Coins(2800f, 1000f, Coins.Coin.Normal),
					new Coins(9000f, 1700f, Coins.Coin.Normal),
					new Coins(7800f, 600f, Coins.Coin.Normal),
					new Coins(10000f, 464f, Coins.Coin.Normal),
					new Coins(16000f, 600f, Coins.Coin.Normal),
					new Coins(15300f, 1500, Coins.Coin.Normal),
					new Coins(19500f, 400f, Coins.Coin.Normal) },

					new EnemyObject[] {

							new EnemyObject(3500f, 1000f,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(9900f, 100f,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(16200f, 1000,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(6800f, 700f,
									EnemyObject.EnemyStatus.Normal) }),

			new LevelDef(4, 1, new float[] { 10f, 100f, 0f, 0.9f, 10f, 3100f,
					3190f, 0f, 1f, 10f }, 1, new DeathsInLevelDef[] {
					new DeathsInLevelDef(1700f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(2700f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(6000f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(12000f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(9500f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(7700f, 420f, 500f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(1500f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(18400f, 1100f, 300f, 900f,
							DeathsInLevelDef.BeamType.MetalStatic),

			}, new BeamsInLevelDef[] {
					new BeamsInLevelDef(1000f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(1600f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(2200f, 932f, 450f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(2750f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(3400f, 900f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(3900f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(4700f, 550f, 400f, 15f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(5400f, 900f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(6200f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(6700f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(7300f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(7700f, 400f, 500f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(7800f, 1300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(8500f, 1500f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(9200f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(9400f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(10000f, 350f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(10700f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(11300f, 950f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(12000f, 650f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(12600f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(13100f, 400f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(13900f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(14200f, 850f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(14900f, 1200f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(15500f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(16100f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(16800f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(17500f, 950f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18000f, 1100f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18100f, 500f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18500f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(19300f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(19300f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

			},

			new Coins[] {

			new Coins(2800f, 1000f, Coins.Coin.Normal),
					new Coins(9000f, 1700f, Coins.Coin.Normal),
					new Coins(7800f, 600f, Coins.Coin.Normal),
					new Coins(10000f, 464f, Coins.Coin.Normal),
					new Coins(16000f, 600f, Coins.Coin.Normal),
					new Coins(15300f, 1500, Coins.Coin.Normal),
					new Coins(19500f, 400f, Coins.Coin.Normal) },

					new EnemyObject[] {

							new EnemyObject(3500f, 1000f,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(9900f, 100f,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(16200f, 1000,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(6800f, 700f,
									EnemyObject.EnemyStatus.Normal) }),

			new LevelDef(5, 1, new float[] { 10f, 100f, 0f, 0.9f, 10f, 3100f,
					3190f, 0f, 1f, 10f }, 1, new DeathsInLevelDef[] {
					new DeathsInLevelDef(1700f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(2700f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(6000f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(12000f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(9500f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(7700f, 420f, 500f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(1500f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(18400f, 1100f, 300f, 900f,
							DeathsInLevelDef.BeamType.MetalStatic),

			}, new BeamsInLevelDef[] {
					new BeamsInLevelDef(1000f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(1600f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(2200f, 932f, 450f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(2750f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(3400f, 900f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(3900f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(4700f, 550f, 400f, 15f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(5400f, 900f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(6200f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(6700f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(7300f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(7700f, 400f, 500f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(7800f, 1300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(8500f, 1500f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(9200f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(9400f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(10000f, 350f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(10700f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(11300f, 950f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(12000f, 650f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(12600f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(13100f, 400f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(13900f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(14200f, 850f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(14900f, 1200f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(15500f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(16100f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(16800f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(17500f, 950f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18000f, 1100f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18100f, 500f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18500f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(19300f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(19300f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

			},

			new Coins[] {

			new Coins(2800f, 1000f, Coins.Coin.Normal),
					new Coins(9000f, 1700f, Coins.Coin.Normal),
					new Coins(7800f, 600f, Coins.Coin.Normal),
					new Coins(10000f, 464f, Coins.Coin.Normal),
					new Coins(16000f, 600f, Coins.Coin.Normal),
					new Coins(15300f, 1500, Coins.Coin.Normal),
					new Coins(19500f, 400f, Coins.Coin.Normal) },

					new EnemyObject[] {

							new EnemyObject(3500f, 1000f,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(9900f, 100f,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(16200f, 1000,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(6800f, 700f,
									EnemyObject.EnemyStatus.Normal) }),
			new LevelDef(6, 1, new float[] { 10f, 100f, 0f, 0.9f, 10f, 3100f,
					3190f, 0f, 1f, 10f }, 1, new DeathsInLevelDef[] {
					new DeathsInLevelDef(1700f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(2700f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(6000f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(12000f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(9500f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(7700f, 420f, 500f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(1500f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(18400f, 1100f, 300f, 900f,
							DeathsInLevelDef.BeamType.MetalStatic),

			}, new BeamsInLevelDef[] {
					new BeamsInLevelDef(1000f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(1600f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(2200f, 932f, 450f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(2750f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(3400f, 900f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(3900f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(4700f, 550f, 400f, 15f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(5400f, 900f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(6200f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(6700f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(7300f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(7700f, 400f, 500f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(7800f, 1300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(8500f, 1500f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(9200f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(9400f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(10000f, 350f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(10700f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(11300f, 950f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(12000f, 650f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(12600f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(13100f, 400f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(13900f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(14200f, 850f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(14900f, 1200f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(15500f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(16100f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(16800f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(17500f, 950f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18000f, 1100f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18100f, 500f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18500f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(19300f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(19300f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

			},

			new Coins[] {

			new Coins(2800f, 1000f, Coins.Coin.Normal),
					new Coins(9000f, 1700f, Coins.Coin.Normal),
					new Coins(7800f, 600f, Coins.Coin.Normal),
					new Coins(10000f, 464f, Coins.Coin.Normal),
					new Coins(16000f, 600f, Coins.Coin.Normal),
					new Coins(15300f, 1500, Coins.Coin.Normal),
					new Coins(19500f, 400f, Coins.Coin.Normal) },

					new EnemyObject[] {

							new EnemyObject(3500f, 1000f,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(9900f, 100f,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(16200f, 1000,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(6800f, 700f,
									EnemyObject.EnemyStatus.Normal) }),
			new LevelDef(7, 1, new float[] { 10f, 100f, 0f, 0.9f, 10f, 3100f,
					3190f, 0f, 1f, 10f }, 1, new DeathsInLevelDef[] {
					new DeathsInLevelDef(1700f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(2700f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(6000f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(12000f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(9500f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(7700f, 420f, 500f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(1500f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(18400f, 1100f, 300f, 900f,
							DeathsInLevelDef.BeamType.MetalStatic),

			}, new BeamsInLevelDef[] {
					new BeamsInLevelDef(1000f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(1600f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(2200f, 932f, 450f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(2750f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(3400f, 900f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(3900f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(4700f, 550f, 400f, 15f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(5400f, 900f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(6200f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(6700f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(7300f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(7700f, 400f, 500f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(7800f, 1300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(8500f, 1500f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(9200f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(9400f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(10000f, 350f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(10700f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(11300f, 950f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(12000f, 650f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(12600f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(13100f, 400f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(13900f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(14200f, 850f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(14900f, 1200f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(15500f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(16100f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(16800f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(17500f, 950f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18000f, 1100f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18100f, 500f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18500f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(19300f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(19300f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

			},

			new Coins[] {

			new Coins(2800f, 1000f, Coins.Coin.Normal),
					new Coins(9000f, 1700f, Coins.Coin.Normal),
					new Coins(7800f, 600f, Coins.Coin.Normal),
					new Coins(10000f, 464f, Coins.Coin.Normal),
					new Coins(16000f, 600f, Coins.Coin.Normal),
					new Coins(15300f, 1500, Coins.Coin.Normal),
					new Coins(19500f, 400f, Coins.Coin.Normal) },

					new EnemyObject[] {

							new EnemyObject(3500f, 1000f,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(9900f, 100f,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(16200f, 1000,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(6800f, 700f,
									EnemyObject.EnemyStatus.Normal) }),
			new LevelDef(8, 1, new float[] { 10f, 100f, 0f, 0.9f, 10f, 3100f,
					3190f, 0f, 1f, 10f }, 1, new DeathsInLevelDef[] {
					new DeathsInLevelDef(1700f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(2700f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(6000f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(12000f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(9500f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(7700f, 420f, 500f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),
					new DeathsInLevelDef(1500f, 0f, 400f, 0f,
							DeathsInLevelDef.BeamType.MetalStatic),

					new DeathsInLevelDef(18400f, 1100f, 300f, 900f,
							DeathsInLevelDef.BeamType.MetalStatic),

			}, new BeamsInLevelDef[] {
					new BeamsInLevelDef(1000f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(1600f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(2200f, 932f, 450f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(2750f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(3400f, 900f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(3900f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(4700f, 550f, 400f, 15f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(5400f, 900f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(6200f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(6700f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(7300f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(7700f, 400f, 500f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(7800f, 1300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(8500f, 1500f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(9200f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(9400f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(10000f, 350f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(10700f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(11300f, 950f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(12000f, 650f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(12600f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(13100f, 400f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

					new BeamsInLevelDef(13900f, 700f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(14200f, 850f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(14900f, 1200f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(15500f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(16100f, 300f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(16800f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(17500f, 950f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18000f, 1100f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18100f, 500f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(18500f, 800f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(19300f, 1000f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),
					new BeamsInLevelDef(19300f, 600f, 400f, 0f,
							BeamsInLevelDef.BeamType.MetalStatic),

			},

			new Coins[] {

			new Coins(2800f, 1000f, Coins.Coin.Normal),
					new Coins(9000f, 1700f, Coins.Coin.Normal),
					new Coins(7800f, 600f, Coins.Coin.Normal),
					new Coins(10000f, 464f, Coins.Coin.Normal),
					new Coins(16000f, 600f, Coins.Coin.Normal),
					new Coins(15300f, 1500, Coins.Coin.Normal),
					new Coins(19500f, 400f, Coins.Coin.Normal) },

					new EnemyObject[] {

							new EnemyObject(3500f, 1000f,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(9900f, 100f,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(16200f, 1000,
									EnemyObject.EnemyStatus.Normal),
							new EnemyObject(6800f, 700f,
									EnemyObject.EnemyStatus.Normal) })

	};

	// ====================================================
	// METHODS
	// ====================================================
	public static LevelDef getLevelDef(final int pLevelIndex,
			final int pWorldIndex) {
		for (LevelDef curLevelDef : AvailableLevels)
			if (curLevelDef.doIndicesMatch(pLevelIndex, pWorldIndex))
				return curLevelDef;
		return null;
	}

	public static boolean isNextLevelInCurrentWorld(final LevelDef pCurrentLevel) {
		if (getLevelDef(pCurrentLevel.mLevelIndex + 1,
				pCurrentLevel.mWorldIndex) != null)
			return true;
		else
			return false;
	}

	public static boolean isThereAnotherWorldAfterCurrentWorld(
			final LevelDef pCurrentLevel) {
		if (getLevelDef(0, pCurrentLevel.mWorldIndex + 1) != null)
			return true;
		else
			return false;
	}

	public static int getNumLevelsInWorld(final int pWorldIndex) {
		int LevelCounter = 0;
		for (LevelDef curLevelDef : AvailableLevels)
			if (curLevelDef.mWorldIndex == pWorldIndex)
				LevelCounter++;
		return LevelCounter;
	}

	// ====================================================
	// CLASSES
	// ====================================================
	public static class Coins {
		public enum Coin {
			Normal
		}

		public final float mX;
		public final float mY;
		public final Coin mCoin;

		public Coins(final float pX, final float pY, final Coin pCoin) {
			mX = pX;
			mY = pY;
			mCoin = pCoin;
		}
	}

	public static class EnemyObject {
		public enum EnemyStatus {
			Normal
		}

		public final float mX;
		public final float mY;
		public final EnemyStatus mEnemyStatus;

		public EnemyObject(final float pX, final float pY,
				final EnemyStatus pEnemyStatus) {
			mX = pX;
			mY = pY;
			mEnemyStatus = pEnemyStatus;
		}
	}

	public static class BeamsInLevelDef {
		public enum BeamType {
			MetalStatic, MetalDynamic, WoodenDynamic
		}

		public final float mX;
		public final float mY;
		public final float mLength;
		public final float mRotation;
		public final BeamType mBeamType;

		public BeamsInLevelDef(final float pX, final float pY,
				final float pLength, final float pRotation,
				final BeamType pBeamType) {
			mX = pX;
			mY = pY;
			mLength = pLength;
			mRotation = pRotation;
			mBeamType = pBeamType;
		}
	}

	public static class DeathsInLevelDef {
		public enum BeamType {
			MetalStatic, MetalDynamic, WoodenDynamic
		}

		public final float mX;
		public final float mY;
		public final float mLength;
		public final float mRotation;
		public final BeamType mBeamType;

		public DeathsInLevelDef(final float pX, final float pY,
				final float pLength, final float pRotation,
				final BeamType pBeamType) {
			mX = pX;
			mY = pY;
			mLength = pLength;
			mRotation = pRotation;
			mBeamType = pBeamType;
		}
	}

	public static class LevelDef {
		public final int mLevelIndex;
		public final int mWorldIndex;
		public final float[] mTerrainSlopes;
		public final BeamsInLevelDef[] mBeams;
		public final DeathsInLevelDef[] mDeaths;
		public final Coins[] mCoins;
		public final EnemyObject[] mEnemyObjects;

		public final int mExpectedNumberCratesToCompleteLevel;

		public LevelDef(final int pLevelIndex, final int pWorldIndex,
				final float[] pTerrainSlopes,
				final int pExpectedNumberCratesToCompleteLevel,
				final DeathsInLevelDef[] pDeathsInLevelDef,
				final BeamsInLevelDef[] pBeamsInLevelDef, final Coins[] coins,
				final EnemyObject[] enemyObjects) {
			mLevelIndex = pLevelIndex;
			mWorldIndex = pWorldIndex;
			mTerrainSlopes = pTerrainSlopes;
			mDeaths = pDeathsInLevelDef;
			mExpectedNumberCratesToCompleteLevel = pExpectedNumberCratesToCompleteLevel;
			mBeams = pBeamsInLevelDef;
			mCoins = coins;
			mEnemyObjects = enemyObjects;
		}

		public boolean doIndicesMatch(final int pLevelIndex,
				final int pWorldIndex) {
			if (mLevelIndex == pLevelIndex)
				if (mWorldIndex == pWorldIndex)
					return true;
			return false;
		}
	}
}