package dukeprogram.userinterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Animates an object when its position is changed. For instance, when
 * additional items are added to a Region, and the layout has changed, then the
 * layout animator makes the transition by sliding each item into its final
 * place.
 */
public class LayoutAnimator implements ChangeListener<Number>, ListChangeListener<Node> {

    private final boolean canHorizontalSlide;
    private final boolean canVerticalSlide;
    private final int duration;
    private final Map<Node, TranslateTransition> nodeXTransitions = new HashMap<>();
    private final Map<Node, TranslateTransition> nodeYTransitions = new HashMap<>();

    /**
     * Creates a new LayoutAnimator
     * @param canHorizontalSlide whether the elements are allowed to slide horizontally
     *                           if the layout changes
     * @param canVerticalSlide whether the elements are allowed to slide vertically if
     *                         if the layout changes
     * @param milliseconds the duration of the entire transition
     */
    public LayoutAnimator(boolean canHorizontalSlide, boolean canVerticalSlide,
                          int milliseconds) {
        this.canHorizontalSlide = canHorizontalSlide;
        this.canVerticalSlide = canVerticalSlide;
        duration = milliseconds;
    }


    /**
     * Animates all the children of a Region.
     * <code>
     *   VBox myVbox = new VBox();
     *   LayoutAnimator animator = new LayoutAnimator();
     *   animator.observe(myVbox.getChildren());
     * </code>
     *
     * @param nodes
     */
    public void observe(ObservableList<Node> nodes) {
        for (Node node : nodes) {
            this.observe(node);
        }
        nodes.addListener(this);
    }

    /**
     * Observes the node
     * @param n the node to observe
     */
    public void observe(Node n) {
        n.layoutXProperty().addListener(this);
        n.layoutYProperty().addListener(this);
    }

    /**
     * Stops animating all the children
     * @param nodes the observable list of nodes to stop animating
     */
    public void unobserve(ObservableList<Node> nodes) {
        nodes.removeListener(this);
    }

    /**
     * Unobserve the node
     * @param n the node to stop observing
     */
    public void unobserve(Node n) {
        n.layoutXProperty().removeListener(this);
        n.layoutYProperty().removeListener(this);
    }

    @Override
    public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
        final double delta = newValue.doubleValue() - oldValue.doubleValue();
        final DoubleProperty doubleProperty = (DoubleProperty) ov;
        final Node node = (Node) doubleProperty.getBean();

        TranslateTransition t = null;
        boolean isFirstOccurrence = false;
        if (doubleProperty.getName().equals("layoutX") && canHorizontalSlide) {
            t = nodeXTransitions.get(node);
            if (t == null) {
                t = new TranslateTransition(Duration.millis(duration), node);
                t.setToX(0);
                nodeXTransitions.put(node, t);
                isFirstOccurrence = true;
            }
            t.setFromX(node.getTranslateX() - delta);

            if (!isFirstOccurrence) {
                node.setTranslateX(node.getTranslateX() - delta);
            }
        } else if (doubleProperty.getName().equals("layoutY") && canVerticalSlide) {
            t = nodeYTransitions.get(node);
            if (t == null) {
                t = new TranslateTransition(Duration.millis(duration), node);
                t.setToY(0);
                nodeYTransitions.put(node, t);
                isFirstOccurrence = true;
            }
            t.setFromY(node.getTranslateY() - delta);

            if (!isFirstOccurrence) {
                node.setTranslateY(node.getTranslateY() - delta);
            }
        }

        if (!isFirstOccurrence && t != null) {
            t.playFromStart();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onChanged(Change change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (Node node : (List<Node>) change.getAddedSubList()) {
                    this.observe(node);
                }
            } else if (change.wasRemoved()) {
                for (Node node : (List<Node>) change.getRemoved()) {
                    this.unobserve(node);
                }
            }
        }
    }
}
