import java.awt.*;
import java.util.Random;
/**
 * Represents a reward entity in the game. Rewards are objects that the main character can collect to gain points or help unlock the space station.
 * This class defines the properties and behaviors of a reward, including its score amount, activation and deactivation times,
 * reward type, and current state (Active, Deactivate, or Waiting).
 */

public class Reward extends Entity{
    private int scoreAmt;
    private long deactivateTime;
    private long activateTime;

    private Constants.REWARD_TYPE type;

    public enum Reward_state{Active, Deactivate, Waiting}
    private Reward_state rewardState;

    /**
     * Constructs a Reward object.
     *
     * @param scoreAmt The amount of score to player the reward provides.
     * @param x        The x-coordinate of the reward.
     * @param y        The y-coordinate of the reward.
     */
    public Reward(int scoreAmt,int x, int y){
        this.scoreAmt = scoreAmt;
        this.x = x;
        this.y = y;
        this.activateTime = 0;
        //set random deactivate time
        Random rand = new Random();
        this.deactivateTime = rand.nextInt(5,8);;
        this.rewardState = Reward_state.Active;
        this.type = Constants.REWARD_TYPE.GENERIC;

    }

    /**
     * Gets the type of the reward.
     *
     * @return The type of the reward.
     */

    public Constants.REWARD_TYPE getRewardType(){
        return this.type;
    }

    /**
     * Set the reward type
     * @param type
     */
    public void setRewardType(Constants.REWARD_TYPE type){
        this.type = type;
    }

    /**
     * sets the amount of score a player gets
     * @param scoreAmt
     */
    public void setScoreAmt(int scoreAmt){
        this.scoreAmt = scoreAmt;
    }

    /**
     * Sets activateTime var
     * @param time the time at which the reward starts being active from
     */
    public void setActivateTime(long time){
        this.activateTime = time;
    }

    /**
     * returns activateTime var
     * @return activateTime the time at which the reward starts being active from
     */
    public long getActivateTime(){
        return this.activateTime;
    }
    /**
     * Sets deactivateTime var
     * @param time the time at which the reward stops being active from
     */
    public void setDeactivateTime(long time){
        this.deactivateTime = time;
    }

    /**
     * returns deactivateTime var
     * @return deactivateTime the time at which the reward stops being deactive from
     */
    public long getDeactivateTime(){
        return this.deactivateTime;
    }

    /**
     * Gets the current state of the reward.
     *
     * @return The current state of the reward (Active, Deactivate, or Waiting).
     */

    public Reward_state getRewardState(){
        return this.rewardState;
    }

    /**
     * Sets the state of the reward.
     *
     * @param rewardState The new state of the reward (Active, Deactivate, or Waiting).
     */
    public void setRewardState(Reward_state rewardState){
        this.rewardState = rewardState;
    }

    /**
     * Draws the reward on the graphics context based on its current state and activation time.
     *
     * @param g           The graphics context on which to draw the reward.
     * @param currentTime The current time in the game. This dictates,whether to draw reward or not
     *                    based on rewards activation time and deactivation time.
     */
    public void draw(Graphics g, long currentTime){
        if ((currentTime >= deactivateTime | currentTime < activateTime) & rewardState == Reward_state.Active & getRewardType() == Constants.REWARD_TYPE.BONUS_REWARD){
            rewardState = Reward_state.Deactivate;

        }
        else if(rewardState == Reward_state.Deactivate & getRewardType() == Constants.REWARD_TYPE.BONUS_REWARD){
            //after reward disappears, spawn reward two seconds after current time
            Random rand = new Random();
            int timeUntilNextActivateTime = rand.nextInt(1,4);
            int rewardDuration = rand.nextInt(5, 10);
            setActivateTime(currentTime + timeUntilNextActivateTime);
            setDeactivateTime(currentTime + timeUntilNextActivateTime + rewardDuration);

            resetToRandomCoordinate();
            rewardState = Reward_state.Waiting;
        }
        else if(rewardState == Reward_state.Waiting & currentTime >= this.activateTime & getRewardType() == Constants.REWARD_TYPE.BONUS_REWARD){
            rewardState = Reward_state.Active;
        }


    }

    /***
     * resets the coordinates of Reward
     */

    public void resetToRandomCoordinate(){
        Random rand = new Random();
        int randomX = rand.nextInt(Constants.RANDOM_VALUE) * 50;
        int randomY = rand.nextInt(Constants.RANDOM_VALUE) * 50;
        this.x = randomX;
        this.y = randomY;

    }

    /**
     * getter for scoreAmt
     * @return scoreAmt
     */
    public int getScoreAmt(){
        return this.scoreAmt;
    }



}
