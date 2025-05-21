export default function Layout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <>
    <div className="h-full w-full">
      <div className="text-m p-1">상품관리 &gt; 출고상품</div>
      <div className="relative mt-4">
        {children}
      </div>
    </div>
    </>
  );
}
