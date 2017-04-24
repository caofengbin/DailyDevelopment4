package myscript
import org.gradle.api.*
import org.gradle.api.tasks.*

class DeleteLogTask extends DefaultTask {

    def suffix = '.java'                            // 遍历.java文件
    def charset = "utf-8"                           // 文件编码格式
    def head = 'Log.'                               // 使用系统API的log类型
    def head2 = 'QLog.'                             // 使用TPFoundation中的API Log类型
    def tailEnd = ');'                             // Log日志代码结束标识

    @TaskAction
    def deleteLog(){
        def rootPath = project.projectDir.absolutePath;
        def files = new File(rootPath + "/src");

        println files.getAbsolutePath()

        deleteLogs(files)
    }

    /**
     * 对src文件结构树状遍历
     * @param listFiles         src文件夹所处目录
     */
    void deleteLogs(File listFiles) {
        if (listFiles != null) {
            listFiles.eachFile {File file ->
                if (file != null) {
                    if (file.isFile()) {
                        if (file.canRead() && file.name.endsWith(suffix)) {
                            println file.getAbsolutePath()
                            deleteFileLog(file);
                        }
                    } else if (file.isDirectory()) {
                        // 递归遍历
                        deleteLogs(file);
                    }
                }
            }
        }
    }

    /**
     * 对单个.java文件遍历处理
     * @param javaFile          文件数中的单个文件
     */
    void deleteFileLog(File javaFile) {
        def endFlag = 0;
        File fileTmp = new File(javaFile.getAbsolutePath() + ".tmp");
        def printWriter = fileTmp.newPrintWriter(charset);
        def reader = javaFile.newReader(charset);
        def tempLine = null;
        String line;
        while ((line = reader.readLine()) != null) {
            if (line != null) {
                tempLine = line.trim();
                if (tempLine.startsWith(head) || tempLine.startsWith(head2)
                        || endFlag == 1) {

                    if (tempLine.endsWith(tailEnd)) {
                        endFlag = 0;
                        printWriter.write("//\n")
                        continue
                    } else {
                        endFlag = 1;
                        continue
                    }
                } else {
                    printWriter.write(line + "\n");
                }
            }
        }

        reader.close();

        printWriter.flush();
        printWriter.close();

        javaFile.delete();
        fileTmp.renameTo(javaFile.getAbsolutePath());
    }
}

