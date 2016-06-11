package arff;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import classify.Path;
import ExtractFeaturesKaiKai.SourceVisitor;

public class Arff {
	static String directoryPath;
	static List<File> fileList = new ArrayList<File>();
	static Map<Integer, Integer> filevec;

	public static void main(String[] args) throws IOException {
		arff();
	}

	public static void arff() throws IOException {
		File fileout1 = new File("ARFF\\train\\"
				+ basename(Path.getRightPath()) + ".arff"); // 学習用ARFF
		File fileout2 = new File("ARFF\\test\\" + basename(Path.getTestPath())
				+ ".arff"); // テストデータ用ARFF
		FileWriter filewriter1 = new FileWriter(fileout1);
		FileWriter filewriter2 = new FileWriter(fileout2);
		BufferedWriter bw1 = new BufferedWriter(filewriter1);
		BufferedWriter bw2 = new BufferedWriter(filewriter2);
		PrintWriter pw1 = new PrintWriter(bw1);
		PrintWriter pw2 = new PrintWriter(bw2);
		WriteData(pw1);
		WriteData(pw2);

		File directory = null;
		for (int j = 2; j < 3; j++) {
			switch (j) {
			case 0:
				directoryPath = Path.getRightPath();
				break;
			case 1:
				directoryPath = Path.getWrongPath();
				break;
			case 2:
				directoryPath = Path.getTestPath();
				break;
			}
			directory = null;
			fileList.clear();

			directory = new File(directoryPath);
			if (directory.exists())
				makeFileList(directory);
			else {
				System.err.println("target directory does not exits.");
				System.exit(1);
			}
			for (File file : fileList)
				System.out.println(file.getName().toString());

			for (File file : fileList) {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				StringBuffer sb = new StringBuffer();
				String st = null;
				while ((st = reader.readLine()) != null) {
					sb.append(st + System.getProperty("line.separator")); // line.separatorで改行コードを取得
				}
				reader.close();
				ASTParser parser = ASTParser.newParser(AST.JLS4);
				parser.setSource(sb.toString().toCharArray());
				CompilationUnit unit = (CompilationUnit) parser
						.createAST(new org.eclipse.core.runtime.NullProgressMonitor());
				SourceVisitor visitor = new SourceVisitor(unit);
				unit.accept(visitor);
				int ValNum[] = new int[85];

				filevec = visitor.vector.getVector();
				for (int key = 1; key <= 84; key++) {
					if (filevec.containsKey(key)) {
						ValNum[key] += filevec.get(key);
					}
				}
				for (int i = 1; i <= 85; i++) {
					if (i < 85) {
						if (i == 29 || i == 63 || i == 64)
							continue;
						if (j == 0 || j == 1)
							pw1.print(ValNum[i] + ",");
						else
							pw2.print(ValNum[i] + ",");
					} else {
						switch (j) {
						case 0:
							pw1.print("TRUE");
							break;
						case 1:
							pw1.print("FALSE");
							break;
						case 2:
							pw2.print("?");
							break;
						}
					}
				}
				if (j == 0 || j == 1)
					pw1.println("");
				else
					pw2.println("");
			}
		}
		pw1.close();
		pw2.close();
	}

	public static void makeFileList(File file) {
		if (file.isDirectory()) {
			File[] innerFiles = file.listFiles();
			for (File tmp : innerFiles) {
				makeFileList(tmp);
			}
		} else if (file.isFile()) {
			if (file.getName().endsWith(".java")) {
				fileList.add(file);
			}
		}
	}

	public static String basename(String path) {
		File file = new File(path);
		return file.getName();
	}

	public static void WriteData(PrintWriter pw) {
		pw.println("@relation SourceStateVector\n");
		// 1
		pw.println("@attribute ANONYMOUS_CLASSDECLARATION numeric");
		pw.println("@attribute ARRAY_ACCESS numeric");
		pw.println("@attribute ARRAY_CREATION  numeric");
		pw.println("@attribute ARRAY_INITIALIZER   numeric");
		pw.println("@attribute ARRAY_TYPE numeric");

		pw.println("@attribute ASSERT_STATEMENT numeric");
		pw.println("@attribute ASSIGNMENT numeric");
		pw.println("@attribute BLOCK numeric");
		pw.println("@attribute BOOLEAN_LITERAL numeric");
		pw.println("@attribute BREAK_STATEMENT numeric\n");
		// 11
		pw.println("@attribute CAST_EXPRESSION numeric");
		pw.println("@attribute CATCH_CLAUSE  numeric");
		pw.println("@attribute CHARACTER_LITERAL numeric");
		pw.println("@attribute CLASS_INSTANCE_CREATION numeric");
		pw.println("@attribute COMPILATION_UNIT  numeric");

		pw.println("@attribute CONDITIONAL_EXPRESSION numeric");
		pw.println("@attribute CONSTRUCTOR_INVOCATION  numeric");
		pw.println("@attribute CONTINUE_STATEMENT numeric");
		pw.println("@attribute DO_STATEMENT numeric");
		pw.println("@attribute EMPTY_STATEMENT  numeric\n");

		// 21
		pw.println("@attribute EXPRESSION_STATEMENT numeric");
		pw.println("@attribute FIELD_ACCESS numeric");
		pw.println("@attribute FIELD_DECLARATION numeric");
		pw.println("@attribute FOR_STATEMENT  numeric");
		pw.println("@attribute IF_STATEMENT numeric");

		pw.println("@attribute IMPORT_DECLARATION numeric");
		pw.println("@attribute INFIX_EXPRESSION numeric");
		pw.println("@attribute INITIALIZER numeric");
		// pw.println("@attribute JAVADOC numeric");
		pw.println("@attribute LABELED_STATEMENT numeric\n");

		// 31
		pw.println("@attribute METHOD_DECLARATION numeric");
		pw.println("@attribute METHOD_INVOCATION numeric");
		pw.println("@attribute NULL_LITERAL numeric");
		pw.println("@attribute NUMBER_LITERAL numeric");
		pw.println("@attribute PACKAGE_DECLARATION numeric");

		pw.println("@attribute PARENTHESIZED_EXPRESSION numeric");
		pw.println("@attribute POSTFIX_EXPRESSION numeric");
		pw.println("@attribute PREFIX_EXPRESSION numeric");
		pw.println("@attribute PRIMITIVE_TYPE numeric");
		pw.println("@attribute QUALIFIED_NAME numeric\n");

		// 41
		pw.println("@attribute RETURN_STATEMENT numeric");
		pw.println("@attribute SIMPLE_NAME numeric");
		pw.println("@attribute SIMPLE_TYPE numeric");
		pw.println("@attribute SINGLE_VARIABLE_DECLARATION numeric");
		pw.println("@attribute STRING_LITERAL numeric");

		pw.println("@attribute SUPER_CONSTRUCTOR_INVOCATION numeric");
		pw.println("@attribute SUPER_FIELD_ACCESS numeric\n");
		pw.println("@attribute SUPER_METHOD_INVOCATION numeric");
		pw.println("@attribute SWITCH_CASE numeric");
		pw.println("@attribute SWITCH_STATEMENT numeric\n");

		// 51
		pw.println("@attribute SYNCHRONIZED_STATEMENT numeric");
		pw.println("@attribute THIS_EXPRESSION numeric");
		pw.println("@attribute THROW_STATEMENT numeric");
		pw.println("@attribute TRY_STATEMENT numeric");
		pw.println("@attribute TYPE_DECLARATION  numeric");

		pw.println("@attribute TYPE_DECLARATION_STATEMENT numeric");
		pw.println("@attribute TYPE_LITERAL numeric");
		pw.println("@attribute VARIABLE_DECLARATION_EXPRESSION numeric");
		pw.println("@attribute VARIABLE_DECLARATION_FRAGMENT numeric");
		pw.println("@attribute VARIABLE_DECLARATION_STATEMENT numeric\n");

		// 60
		pw.println("@attribute WHILE_STATEMENT numeric");
		pw.println("@attribute INSTANCEOF_EXPRESSION numeric");
		// pw.println("@attribute LINE_COMMENT numeric");
		// pw.println("@attribute BLOCK_COMMENT numeric");
		pw.println("@attribute TAG_ELEMENT numeric");

		pw.println("@attribute TEXT_ELEMENT numeric");
		pw.println("@attribute MEMBER_REF numeric");
		pw.println("@attribute METHOD_REF numeric");
		pw.println("@attribute METHOD_REF_PARAMETER numeric");
		pw.println("@attribute ENHANCED_FOR_STATEMENT numeric\n");

		// 70
		pw.println("@attribute ENUM_DECLARATION numeric");
		pw.println("@attribute ENUM_CONSTANT_DECLARATION numeric");
		pw.println("@attribute TYPE_PARAMETER numeric");
		pw.println("@attribute PARAMETERIZED_TYPE numeric");
		pw.println("@attribute QUALIFIED_TYPE numeric");

		pw.println("@attribute WILDCARD_TYPE numeric");
		pw.println("@attribute NORMAL_ANNOTATION numeric");
		pw.println("@attribute MARKER_ANNOTATION numeric");
		pw.println("@attribute SINGLE_MEMBER_ANNOTATION numeric");
		pw.println("@attribute MEMBER_VALUE_PAIR numeric\n");

		// 80
		pw.println("@attribute ANNOTATION_TYPE_DECLARATION numeric");
		pw.println("@attribute ANNOTATION_TYPE_MEMBER_DECLARATION numeric");
		pw.println("@attribute MODIFIER numeric");
		pw.println("@attribute UNION_TYPE numeric\n");
		pw.println("@attribute GENERATED {TRUE,FALSE}\n");

		pw.println("@data");
	}
}
