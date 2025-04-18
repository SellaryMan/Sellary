import SearchBox from "@/components/search/SearchBox";
export default function Layout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <>
      <SearchBox/>
      {children}
    </>
  );
}
