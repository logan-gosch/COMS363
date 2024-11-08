SELECT d.name, d.level, COUNT(m.snum) AS student_count
FROM degrees d
JOIN major m ON d.name = m.name AND d.level = m.level
GROUP BY d.name, d.level
ORDER BY student_count DESC, d.name;
