package _08树结构基础._01二叉树基础;

public class Node <E> {

    private E element;

    private Node<E> left;

    private Node<E> right;

    Node(E element, Node<E> left, Node<E> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }


    public Node<E> getLeft() {
        return left;
    }

    public Node<E> setLeft(Node<E> left) {
        this.left = left;
        return this;
    }

    public Node<E> getRight() {
        return right;
    }

    public Node<E> setRight(Node<E> right) {
        this.right = right;
        return this;
    }

    @Override
    public String toString() {
        return element.toString();
    }

    /**
     * <p>前序遍历</p>
     * <p>1、输出当前结点</p>
     * <p>2、递归向左子树前序遍历</p>
     * <p>3、递归向右子树前序遍历</p>
     */
     public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * <p>中序遍历</p>
     * <p>1、递归向左子树前序遍历</p>
     * <p>2、输出当前结点</p>
     * <p>3、递归向右子树前序遍历</p>
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * <p>后序遍历</p>
     * <p>1、递归向左子树前序遍历</p>
     * <p>2、递归向右子树前序遍历</p>
     * <p>3、先输出当前结点</p>
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }





    public Node<E> preSearch(E element) {
        if (this.element.equals(element)) {
            return this;
        }
        Node<E> resultNode = null;
        if (this.left != null) {
            resultNode = this.left.preSearch(element);
        }
        // 说明左子树找到
        if (null != resultNode) {
            return resultNode;
        }
        if (this.right != null) {
            resultNode = this.right.preSearch(element);
        }
        return resultNode;
    }

    public Node<E> infixSearch(E element) {
        Node<E> resultNode = null;
        if (this.left != null) {
            resultNode = this.left.infixSearch(element);
        }
        // 说明左子树找到
        if (null != resultNode) {
            return resultNode;
        }
        if (this.element.equals(element)) {
            return this;
        }
        if (this.right != null) {
            resultNode = this.right.infixSearch(element);
        }
        return resultNode;
    }

    public Node<E> postSearch(E element) {
        Node<E> resultNode = null;
        if (this.left != null) {
            resultNode = this.left.postSearch(element);
        }
        // 说明左子树找到
        if (null != resultNode) {
            return resultNode;
        }
        if (this.right != null) {
            resultNode = this.right.postSearch(element);
        }
        if (this.element.equals(element)) {
            return this;
        }
        return resultNode;
    }

    public Node<E> remove(E element) {
        /*
        判断左子节点是否与目标元素相同
        但是要先判断左子节点是否存在
         */
        if (this.left != null && this.left.element.equals(element)) {
            Node<E> result = this.left;
            // 让左子节点为空
            this.left = null;
            return result;
        }
        // 同上
        if (this.right != null && this.right.element.equals(element)) {
            Node<E> result = this.right;
            this.right = null;
            return result;
        }

        // 当左右子节点都和指定结点不相同时，执行如下
        // 先向左遍历，再向右
        if (this.left != null) {
            this.left.remove(element);
        }
        if (this.right != null) {
            this.right.remove(element);
        }
        return null;
    }

    public Node<E> remove2(E element) {
        // 判断当前结点是否为指定结点
        if (this.element.equals(element)) {
            Node<E> result = this;
            /*
            1、右子节点为空、左子节点为空，当前结点被左子节点替代
            2、左右子节点均不为空，当前结点被左子节点替代
             */
            if (this.left != null) {
                this.element = result.left.element;
                this.left = result.left.left;
            } else
            // 左子节点为空，右子节点不为空，当前结点被右子节点代替
            if (this.right != null) {
                this.element = result.right.element;
                this.right = result.right.right;
            }
            return result;
        }

        // 判断左子节点是否为指定结点
        if (this.left != null && this.left.element.equals(element)) {
            Node<E> temp = this.left;
            // 判断左子节点是否为叶节点
            if (temp.left == null && temp.right == null) {
                this.left = null;
                return temp;
            }
        }

        // 同上
        if (this.right != null && this.right.element.equals(element)) {
            Node<E> temp = this.right;
            if (temp.left == null && temp.right == null) {
                this.right = null;
                return temp;
            }
        }

        if (this.left != null) {
            this.left.remove2(element);
        }
        if (this.right != null) {
            this.right.remove2(element);
        }
        return null;
    }

}
