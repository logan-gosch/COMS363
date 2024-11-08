SELECT d.name, d.level, COUNT(*) AS student_count
FROM degrees d
LEFT JOIN major m ON d.name = m.name AND d.level = m.level
LEFT JOIN minor n ON d.name = n.name AND d.level = n.level
GROUP BY d.name, d.level
ORDER BY student_count DESC, d.name;
