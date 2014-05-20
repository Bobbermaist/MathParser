package it.mathparser.math;

import java.util.LinkedList;

public class ExpressionTree implements ParametricExpression {

	private Node root;
	
	private ParametricNode current;
	
	private NodeFactory factory = new NodeFactory();
	
	public void addOperator(Operator operator) {
		SimpleExpression newExpression = new SimpleExpression();
		newExpression.setOperator(operator);
		ParametricNode newNode = (ParametricNode) this.factory.nodeInstance(newExpression);
		
		if (this.root == null) {
			this.root = newNode;
			this.updateCurrent(this.root);
			return;
		}
		
		if (this.current == null) {
			newNode.addArgument(this.root);
			this.root = newNode;
			this.updateCurrent(this.root);
			return;
		}
		
		if (this.current.missingOperator()) {
			this.current.setOperator(operator);
			return;
		}
		
		if (operator instanceof UnaryOperator || current.getOperator() instanceof UnaryOperator) {
			this.addArgument(newExpression);
			return;
		}
		
		if (this.current.hasPriorityOver(newExpression)) {
			ParametricNode parent = this.current.getParent();
			if (parent == null) {
				newNode.addArgument(this.root);
				this.root = newNode;
			} else {
				parent.pollLastArgument();
				parent.addArgument(newNode);
				newNode.addArgument(this.current);
				this.current.setParent(newNode);
			}
		} else {
			Expression lastArgument = this.current.pollLastArgument();
			newNode.addArgument(lastArgument);
			this.current.addArgument(newNode);
		}
		
		this.updateCurrent(newNode);
	}

	private void updateCurrent(Node node) {
		if (node instanceof ParametricNode) {
			this.current = (ParametricNode) node;
		}
	}
	
	@Override
	public void addArgument(Expression expression) {
		Node newNode = factory.nodeInstance(expression);
		
		if (this.root == null) {
			this.root = newNode;
		} else  {
			this.current.addArgument(newNode);
		}
		
		this.updateCurrent(newNode);
	}

	@Override
	public Operator getOperator() {
		if (this.root instanceof ParametricNode) {
			return ((ParametricNode) this.root).getInfo().getOperator();
		} else {
			return null;
		}
	}

	@Override
	public void setOperator(Operator operator) {
		if (this.root instanceof ParametricNode) {
			((ParametricNode) this.root).getInfo().setOperator(operator);
		} else {
			throw new RuntimeException("Unable to set an operator to a non parametric node");
		}
	}

	@Override
	public int getNumArgs() {
		if (this.root instanceof ParametricNode) {
			return ((ParametricNode) this.root).getInfo().getNumArgs();
		} else {
			return 0;
		}
	}

	@Override
	public Expression pollLastArgument() {
		if (this.root instanceof ParametricNode) {
			return ((ParametricNode) this.root).getInfo().pollLastArgument();
		} else {
			return null;
		}
	}

	@Override
	public boolean hasPriorityOver(ParametricExpression expression) {
		if (this.root instanceof ParametricNode) {
			return ((ParametricNode) this.root).getInfo().hasPriorityOver(expression);
		} else {
			return false;
		}
	}
	
	@Override
	public double solve() {
		return this.root.solve();
	}
	
	@Override
	public String toString() {
		return this.root.toString();
	}
	
	private class NodeFactory {
		
		public Node nodeInstance(Expression expression) {
			if (expression instanceof ParametricNode) {
				return new ParametricNode((ParametricNode) expression);
			} else if (expression instanceof Node) {
				return new Node((Node) expression);
			} else if (expression instanceof ParametricExpression) {
				return new ParametricNode((ParametricExpression) expression);
			} else {
				return new Node(expression);
			}
		}
	}
	
	private class Node implements Expression {
		
		private Expression info;
		
		private ParametricNode parent;
		
		public Node() {}
		
		public Node(Expression info) {
			this.info = info;
		}
		
		public Node(Node clone) {
			this.info = clone.info;
			this.parent = clone.parent;
		}
		
		public ParametricNode getParent() {
			return this.parent;
		}
		
		public void setParent(ParametricNode parent) {
			this.parent = parent;
		}
		
		@Override
		public double solve() {
			return this.info.solve();
		}
		
		@Override
		public String toString() {
			return this.info.toString();
		}
	}
	
	private class ParametricNode extends Node implements ParametricExpression {
		
		private ParametricExpression info;
		
		public ParametricNode(ParametricExpression info) {
			this.info = info;
		}
		
		public ParametricNode(ParametricNode clone) {
			this.info = clone.info;
			this.setParent(clone.getParent());
		}
		
		public ParametricExpression getInfo() {
			return this.info;
		}
		
		public boolean missingOperator() {
			return this.getOperator() == null;
		}
		
		@Override
		public Operator getOperator() {
			return this.info.getOperator();
		}
		
		@Override
		public int getNumArgs() {
			return this.info.getNumArgs();
		}
		
		@Override
		public void addArgument(Expression expression) {
			Node argument = (Node) expression;
			argument.parent = this;
			this.info.addArgument(argument);
		}
		
		@Override
		public Expression pollLastArgument() {
			return this.info.pollLastArgument();
		}
		
		@Override
		public void setOperator(Operator operator) {
			this.info.setOperator(operator);
		}
		
		@Override
		public boolean hasPriorityOver(ParametricExpression expression) {
			return this.info.hasPriorityOver(expression);
		}
		
		@Override
		public double solve() {
			return this.info.solve();
		}
		
		@Override
		public String toString() {
			return this.info.toString();
		}
	}
	
	private class SimpleExpression implements ParametricExpression {
		
		private Operator operator;
		
		private LinkedList<Expression> arguments;
		
		private int numArgs = 0;
		
		public SimpleExpression() {
			this.arguments = new LinkedList<Expression>();
		}
		
		@Override
		public Operator getOperator() {
			return this.operator;
		}
		
		@Override
		public int getNumArgs() {
			return this.numArgs;
		}
		
		@Override
		public void setOperator(Operator operator) {
			this.operator = operator;
		}
		
		@Override
		public void addArgument(Expression expression) {
			this.numArgs++;
			this.arguments.add(expression);
		}
		
		@Override
		public boolean hasPriorityOver(ParametricExpression expression) {
			if (this.operator.getPriority() < expression.getOperator().getPriority()) {
				return false;
			}
			return true;
		}
		
		@Override
		public Expression pollLastArgument() {
			if (this.arguments == null) {
				return null;
			} else {
				return this.arguments.pollLast();
			}
		}
		
		@Override
		public double solve()throws RuntimeException {
			if (this.operator == null) {
				throw new RuntimeException("Missing operator");
			} else {
				return this.operator.execute(this.arguments);
			}
		}
		
		@Override
		public String toString() {
			String operator = (this.operator == null) ? "?" : this.operator.toString();
			return operator + this.arguments.toString();
		}
	}
}
