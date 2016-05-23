package edu.byu.cs.superasteroids.ship_builder;

import java.util.List;

import edu.byu.cs.superasteroids.base.IController;

/**
 * Created by devonkinghorn on 5/22/16.
 */
public class ShipBuildingView implements IShipBuildingView{


  public enum SelectionView {
    MAIN_BODY{
      public void setDirections(){

      }
    },
    EXTRA_PART{
      public void setDirections(){

      }
    },
    CANNON{
      public void setDirections(){

      }
    },
    ENGINE{
      public void setDirections(){

      }
    },
    POWER_CORE{
      public void setDirections(){

      }
    };
    public abstract void setDirections();
  }

//  enum ViewDirection {LEFT, RIGHT, UP, DOWN}
  /**
   * Gets the view's controller
   * @return The view's controller
   */
  public IController getController(){
    return null;
  }

  /**
   * Sets the view's controller
   * @param controller The controller to set
   */
  public void setController(IController controller){

  }
  /**
   * Instructs the ShipBuildingView to animate to a new part selection screen. This function is
   * generally called as a response to a call to the controller's onSlideView() function.
   * @param view The part selection view to animate into view
   * @param animationDirection The direction to use while animating the new view in. EXAMPLE: If
   *                           LEFT is chosen, the view will animate in from the right, moving left
   *                           as it does so.
   */
  public void animateToView(PartSelectionView view, ViewDirection animationDirection){

  }

  /**
   * Instructs the ShipBuildingView to start the game.
   */
  public void startGame(){

  }
  /**
   * Enables or disables the start game button. This function should not be called in the
   * ShipBuildingController's constructor. The start game button is initially disabled. The start
   * game button should be enabled when a complete has been constructed by the user.
   * @param enabled TRUE if the button should be enabled, FALSE otherwise
   */
  public void setStartGameButton(boolean enabled){

  }
  /**
   * Sets the visibility and text of an arrow for a part selection view. To ensure the partView is
   * prepared, make sure to only call this function from the controller's onViewLoaded() function.
   * @param partView The part selection view to set the arrow for
   * @param arrow The arrow to set
   * @param visible TRUE - The arrow and text are visible, FALSE - The arrow and text are invisible
   * @param text The text to display alongside the arrow. NULL or an empty string if there is
   *             no text to display.
   */
  public void setArrow(IShipBuildingView.PartSelectionView partView, IShipBuildingView.ViewDirection arrow, boolean visible, String text){

  }
  /**
   * Sets a part selection views list of image IDs. EXAMPLE: To set the list of image IDs for the
   * Cannon selection view, the parameter view would be PartSelectionView.CANNON and partImageIds
   * would be a list of cannon part image IDs.
   * @param view The view to set the images for.
   * @param partImageIds The list of image IDs to set.
   */
  public void setPartViewImageList(IShipBuildingView.PartSelectionView view, List<Integer> partImageIds){

  }


}
