package com.neo.utils.tools;

/**
 * 主键生成策略
 * @author 罗玉林
 *
 */
public class Sequence {
	private static Sequence idWorker = new Sequence();
	private static Sequence idWorker2 = null;
	private long workerId;
	/*private static final long twepoch = 1361753741828L;*/
	private long sequence = 0L;
/*	private static final long workerIdBits = 8L;
	public static final long maxWorkerId = 255L;
	private static final long sequenceBits = 10L;
	private static final long workerIdShift = 10L;
	private static final long timestampLeftShift = 18L;*/
	public static final long sequenceMask = 1023L;
	private long lastTimestamp = -1L;

	private Sequence(long workerId) {
		if ((workerId > 255L) || (workerId < 0L)) {
			throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", new Object[] { Long.valueOf(255L) }));
		}
		this.workerId = workerId;
	}

	public static Sequence getSequence() {
		return idWorker;
	}

	public static Sequence getSequence(long workerId) {
		if ((idWorker2 == null) || (workerId != idWorker2.workerId))
			idWorker2 = new Sequence(workerId);
		return idWorker2;
	}

	// ERROR //
	private Sequence() {

	}

	public synchronized long nextId() {
		long timestamp = timeGen();

		if (this.lastTimestamp == timestamp) {
			this.sequence = (this.sequence + 1L & 0x3FF);

			if (this.sequence == 0L) {
				timestamp = tilNextMillis(this.lastTimestamp);
			}
		} else {
			this.sequence = 0L;
		}

		if (timestamp < this.lastTimestamp) {
			try {
				throw new Exception(String.format("服务器时间向后调整了，拒绝生成id，调后了 %d毫秒", new Object[] { Long.valueOf(this.lastTimestamp - timestamp) }));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.lastTimestamp = timestamp;

		long nextId = timestamp - 1361753741828L << 18 | this.workerId << 10 | this.sequence;

		return nextId;
	}

	private long tilNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	/**
	 * 当前服务器时间
	 * @return
	 */
	private long timeGen() {
		return System.currentTimeMillis();
	}

}